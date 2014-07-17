package org.esgi.orm;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.esgi.orm.annotations.ORM_PK;
import org.esgi.orm.annotations.ORM_SEARCH_WITHOUT_PK;
import org.esgi.orm.annotations.ORM_TABLE;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ORM implements IORM {

	private static final  String TYPE_FIELD_STRING = "char(50)"; 
	private static final  String TYPE_FIELD_INT = "int"; 
	private static final  String TYPE_FIELD_FLOAT = "float"; 
	private static final  String TYPE_FIELD_DOUBLE = "double"; 
	private static final  String TYPE_FIELD_BOOLEAN = "tinyint(1)"; 
	private static final  String TYPE_FIELD_DATE = "date"; 
	
	private static final String BDD_ADDR = "jdbc:mysql://localhost/java2e";
	private static final String BDD_LOGIN = "java2e";
	private static final String BDD_PASSWORD = "java2e";

	static ORM instance;
	private java.sql.Statement _statement;
	private Connection connection ;

	static {
		instance = new ORM();
		instance.init();
	}

	public static Object save(Object o) {
		return instance._save(o);
	}

	public static Object load(Class clazz, Object id) {
		return instance._load(clazz, id);
	}

	public static Object loadWithOutPrimaryKey(Class clazz, ORM_SEARCH_WITHOUT_PK critere) {
		return instance._loadWithoutPrimaryKey(clazz, critere);
	}
	
	public static boolean remove(Class clazz, Object id) {
		return instance._remove(clazz, id);
	}


	@Override
	/**
	 * Permet le chargement d'un objet enregistrer en base via une seul clé primaire
	 * clazz = nom de la classe, id = clé primaire 
	 */
	public Object _load(Class clazz, Object id) {
		Object o = null;
		try {			
			o = clazz.newInstance();

			String nameTab = getTableName(clazz);

			if(null == nameTab)
				return null;

			//R�cup�ration des champs de la table
			ArrayList<Object[]> tabChamps = getAllField(clazz);

			String listechamps = "";
			ArrayList<String> tabPK = new ArrayList<String>();

			for(Object[] chps : tabChamps){
				listechamps += chps[1] + ",";
				if(chps[2].toString().equals("PRIMARY_KEY"))
					tabPK.add(chps[1].toString());
			}
			listechamps = listechamps.substring(0,listechamps.length()-1);


			//Pr�paration de la requ�te
			String sql = "SELECT " + listechamps +" FROM "+ nameTab +" WHERE";
			for(int i = 0 ; i<tabPK.size() ; i++){
				if(i==0){
					sql += " "+ tabPK.get(i) + " = ?";
				}else{
					sql += " AND "+ tabPK.get(i) + " = ?";	
				}
				
			}
			PreparedStatement stat = (PreparedStatement) this.connection.prepareStatement(sql) ;
			int n = 1;  

			if (!(id instanceof HashMap)){
				stat.setString(n++, id.toString());
			}else{
				HashMap<String, Object> ids = (HashMap<String, Object>)id;
				for(String PKStr : tabPK){
					if(null != ids.get(PKStr)){
						stat.setString(n++, ids.get(PKStr).toString());
					}
				}
			}

			
			ResultSet rs = (ResultSet) stat.executeQuery () ;

			Field[] fields = clazz.getFields();
			while (rs.next ())
			{
				for(Field f : fields){
					if(f.getModifiers() == 1){
						String nameField = namePackageToNamePropertie(f.getName());
						f.set(o, rs.getObject(nameField));
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return o;
	}

	/**
	 * Permet le chargement d'une liste d'objets enregistrés en base sans connaître ses clés primaires, 
	 * Pour cela passer par une HasMap 
	 * avec comme structure :
	 * 		- KEY HASHMAP = COLLUM de la classe 
	 * 		- VALUE HASMAP = VALEUR de critère de selection de la requête SQL
	 */
	public  ArrayList<Object> _loadWithoutPrimaryKey(Class clazz, ORM_SEARCH_WITHOUT_PK critere) {
		
		
		init();
		
		ArrayList<Object> listO =  new ArrayList<>();
		
		try {			
		

			String nameTab = getTableName(clazz);

			if(null == nameTab)
				return null;

			//R�cup�ration des champs de la table
			ArrayList<Object[]> tabChamps = getAllField(clazz);

			String listechamps = "";
			
			
			ArrayList<String> tabPK = new ArrayList<String>();

			for(Object[] chps : tabChamps){
				listechamps += chps[1] + ",";
				if(chps[2].toString().equals("PRIMARY_KEY"))
					tabPK.add(chps[1].toString());
			}
			listechamps = listechamps.substring(0,listechamps.length()-1);
			
			
			
			
			
			//Préparation de la requête
			String sql = "SELECT " + listechamps +" FROM "+ nameTab +" WHERE ";
			int i=0;
			
			ArrayList<String> tempValue = new ArrayList<>();
			
			for(Entry<String, String> entry : critere.getRecherche().entrySet()) {
			    String cle = entry.getKey();
			    tempValue.add(entry.getValue());
			    
			    if(i>0){
			    	sql += " AND " + cle + " = ? ";
			    }else{
			    	sql += " " + cle + " = ? ";
			    }
			    i++;
			}
						
			PreparedStatement stat = (PreparedStatement) this.connection.prepareStatement(sql) ;
			
			
			for(i = 0 ; i<tempValue.size() ; i++){
				stat.setString(i+1, tempValue.get(i));
			}

			
			ResultSet rs = (ResultSet) stat.executeQuery () ;

			
			Object tempO;
			Field[] fields = clazz.getFields();
			while (rs.next ())
			{
				tempO = clazz.newInstance();
				for(Field f : fields){
					if(f.getModifiers() == 1){
						String nameField = namePackageToNamePropertie(f.getName());
						f.set(tempO, rs.getObject(nameField));
					}
				}
				listO.add(tempO);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}

		return listO;
	}

	
	
	
	@Override
	public boolean _remove(Class clazz, Object id) {

		String tableName = getTableName(clazz);

		String sql ="DELETE FROM "+tableName+" WHERE 1 ";

		ArrayList<Object[]> tabChamps = getAllField(clazz);
		ArrayList<String> tabPK = new ArrayList<String>();

		for(Object[] chps : tabChamps){
			if(chps[2].toString().equals("PRIMARY_KEY")){
				tabPK.add(chps[1].toString());
				sql += " AND "+ chps[1].toString() + " = ?";
			}
		}

		try {
			PreparedStatement stat = (PreparedStatement) this.connection.prepareStatement(sql) ;

			int n=1;
			if (!(id instanceof HashMap)){
				stat.setString(n++, id.toString());
			}else{
				HashMap<String, Object> ids = (HashMap<String, Object>)id;
				for(String PKStr : tabPK){
					if(null != ids.get(PKStr)){
						stat.setString(n++, ids.get(PKStr).toString());
					}
				}
			}

			return (stat.executeUpdate()>0);
		} catch (SQLException e) {	}




		return false;
	}


	@Override
	public Object _save(Object o) {
		String nameTable = getTableName(o.getClass());

		ArrayList<Object[]> tabPropriete =  getAllField(o.getClass());

		instance.init();

		instance.createTable(nameTable,tabPropriete);

		o = instance.SaveInsertUpdate(o,nameTable,tabPropriete);

		return o;
	}



	public Object SaveInsertUpdate(Object o,String nameTable, ArrayList<Object[]> tabPropriete){
		String sqlSelect ="", sqlInsert = "";
		ArrayList<String> tabPrimaryKey = new ArrayList<String>();
		HashMap<Field, Object> mapPrimaryKey = new HashMap<>();

		if(null != o){
			for(Field f : o.getClass().getFields()){
				try{
					Object ob = f.get(o);
					if(f.getAnnotation(ORM_PK.class) instanceof ORM_PK){
						if(null != ob){
							
							mapPrimaryKey.put(f, ob);
						}
					}
				}catch(Exception e){

				}
			}
			// Si on poss�de des cl� primaire non null alors UPDATE sinon INSERT
			if(mapPrimaryKey.size() > 0){
				boolean flagPremierPassage = false;
				String sqlClauseWhere =" WHERE ";
				for(Entry<Field, Object> entry : mapPrimaryKey.entrySet()) {
					if(flagPremierPassage == true){
						sqlClauseWhere += " AND ";
					}
					flagPremierPassage = true;
					sqlClauseWhere += entry.getKey().getName()  + " = " + entry.getValue();
				}
				
				boolean flagField = false;
				String value = " ";
				for(Field f : o.getClass().getFields()){
					try{
						if(!(f.getAnnotation(ORM_PK.class) instanceof ORM_PK)){
							
						if(f.getModifiers() == 1){
							Object ob = f.get(o);
							if(flagField == true){
								value += " , ";
							}
							if(null != ob){
								flagField = true;	
							}
							value +=  f.getName() + " = \"" + ob.toString() + "\"" ;
						}
						}
					}catch(Exception e){

					}
				}
				
				String sqlUpdate = " UPDATE " + nameTable + " SET " + value + sqlClauseWhere; 
				
				try {
					_statement = this.connection.createStatement();
				

				_statement.executeUpdate(sqlUpdate);
				
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				boolean flagField = false;
				String value = " VALUES ( ";
				String field = "( ";
				for(Field f : o.getClass().getFields()){
					try{
						if(f.getModifiers() == 1){
							Object ob = f.get(o);
							if(null != ob){
								if(flagField == true){
									value += ",";
									field += ",";
								}
								if(null != ob){
									flagField = true;	
								}
	
								value += "\"" + (ob.toString()) + "\"";
								field += f.getName();
							}
						}
					}catch(Exception e){

					}
				}
				value += " ) ";
				field += " ) ";
				String sqlUpdate = "INSERT INTO " + nameTable + field +value;

				try {
					PreparedStatement pStatement = (PreparedStatement) connection.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
					pStatement.executeUpdate();
					ResultSet rs = pStatement.getGeneratedKeys();

					Integer autoIncKeyFromApi = -1;
					rs = pStatement.getGeneratedKeys();
					if (rs.next()) {
					    autoIncKeyFromApi = rs.getInt(1);
					    for(Field f : o.getClass().getFields()){
							try{
								if(f.getAnnotation(ORM_PK.class) instanceof ORM_PK){
										f.set(o, autoIncKeyFromApi);
								}
							}catch(Exception e){

							}
					    }
					}					
					

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return o;
	}

	public void init(){
		//Chargement du driver
		try { Class.forName("com.mysql.jdbc.Driver").newInstance(); }
		catch (Exception e) { e.printStackTrace(); }
		//Récupération de la connexion à la base de données
		try { 
			this.connection = (Connection) DriverManager.getConnection(BDD_ADDR,BDD_LOGIN,BDD_PASSWORD) ;
		}
		catch (SQLException e) { e.printStackTrace();}
	}

	public void createTable(String nameTable, ArrayList<Object[]> tabPropriete){
		try{
			String sql = "CREATE TABLE IF NOT EXISTS "+ nameTable + " ";
			if(null != tabPropriete){
				boolean flag = false;
				boolean flagPKExist = false;
				String primaryKey  = "";
				sql +="(";
				for(int i=0;i<tabPropriete.size();i++){
					Object[] prop = tabPropriete.get(i);
					if(null != prop[0] || null != prop[1]){
						if(flag == true){
							sql += ",";
						}
						flag = true;   
						sql+=  namePackageToNamePropertie(prop[1].toString()) + " "
								+ formateSQLFieldForType(prop[0].toString().toString()) + " ";
						if(prop[2].equals("PRIMARY_KEY")){
							sql += " AUTO_INCREMENT ";
						}
						if(prop[2].equals("PRIMARY_KEY")){
							if(flagPKExist == true){
								primaryKey +=",";
							}
							flagPKExist = true;
							primaryKey += namePackageToNamePropertie(prop[1].toString());
						}
					}
				}
				if(flagPKExist){
					sql += primaryKey = ", CONSTRAINT PK PRIMARY KEY (" + primaryKey + ") ";
				}
				sql += " )";
			}

			_statement = this.connection.createStatement();

			_statement.executeUpdate(sql);
		}catch ( Exception e){
			System.out.println("erreur create table");
			System.out.println(e.getCause());
		}
	}



	public static String getTableName(Class clazz){
		Annotation[] annotations = clazz.getAnnotations();
		for(Annotation annotation : annotations){
			if(annotation instanceof ORM_TABLE)
				return ((ORM_TABLE) annotation).value();
		}
		return null;
	}

	public static ArrayList<Object[]> getAllField(Class clazz){

		Field tabTempPropriete[] = clazz.getFields();

		ArrayList<Object[]> tabFields = new ArrayList<>();

		for(int i = 0 ; i < tabTempPropriete.length ; i++){  
			if(tabTempPropriete[i].getModifiers() == Modifier.PUBLIC){
				Object[] tabF = new Object[3];
				tabF[0] = (Object) tabTempPropriete[i].getGenericType();
				tabF[1] =  namePackageToNamePropertie(tabTempPropriete[i].toString());
				if(tabTempPropriete[i].getAnnotation(ORM_PK.class) instanceof ORM_PK){
					tabF[2] = "PRIMARY_KEY";


				}else{
					tabF[2] = "";
				}
				tabFields.add(tabF);
			}
		}


		return tabFields;
	}

	public static String namePackageToNamePropertie(String namePackage){
		String nameClasse[] =namePackage.split("\\.");
		return nameClasse[nameClasse.length-1];
	}

	public String formateSQLFieldForType(Object o){
		if(null != o){
			String [] splitNameType = o.toString().split("\\.");
			String name ="";
			if(splitNameType.length > 0){
				name = splitNameType[splitNameType.length-1];
			}else{
				name = o.toString();
			}

			if(name.equals("String")){
				return TYPE_FIELD_STRING;
			}else if(name.equals("Integer")|| name.equals("int")){
				return TYPE_FIELD_INT;
			}else if (name.toLowerCase().equals("boolean") ){
				return TYPE_FIELD_BOOLEAN;
			}else if (name.toLowerCase().equals("float") ){
				return TYPE_FIELD_FLOAT;
			}else if (name.toLowerCase().equals("double") ){
				return TYPE_FIELD_DOUBLE;
			}else if (name.toLowerCase().equals("date") ){
				return TYPE_FIELD_DATE;
			}else{
				return TYPE_FIELD_STRING;
			}
		}
		return null;
	}

}