package org.esgi.orm.my;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.esgi.orm.my.annotations.*;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class ORM implements IORM {

	private static final  String TYPE_FIELD_STRING = "char(50)"; 
	private static final  String TYPE_FIELD_INT = "int"; 
	private static final  String TYPE_FIELD_FLOAT = "float"; 
	private static final  String TYPE_FIELD_BOOLEAN = "tinyint(1)"; 


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

	public static boolean remove(Class clazz, Object id) {
		return instance._remove(clazz, id);
	}


	@Override
	public Object _load(Class clazz, Object id) {
		System.out.println(" ----- Début LOAD ---- ");
		Object o = null;
		try {			
			o = clazz.newInstance();

			String nameTab = getTableName(clazz);

			if(null == nameTab)
				return null;

			//Récupération des champs de la table
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
			String sql = "SELECT " + listechamps +" FROM "+ nameTab +" WHERE 1";
			for(int i = 0 ; i<tabPK.size() ; i++)
				sql += " AND "+ tabPK.get(i) + " = ?";

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
					System.out.println("valeur du champ :" + ob.toString());
					if(f.getAnnotation(ORM_PK.class) instanceof ORM_PK){
						if(null != ob){

							mapPrimaryKey.put(f, ob);
						}
					}
				}catch(Exception e){

				}
			}
			//System.out.println(mapPrimaryKey.);
			// Si on possède des clé primaire non null alors UPDATE sinon INSERT
			if(mapPrimaryKey.size() > 0){

			}else{
				boolean flagField = false;
				String value = " VALUES ( ";
				String field = "( ";
				for(Field f : o.getClass().getFields()){
					try{
						if(f.getModifiers() == 1){
							Object ob = f.get(o);
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
					}catch(Exception e){

					}
				}
				value += " ) ";
				field += " ) ";
				String sqlUpdate = "INSERT INTO " + nameTable + field +value;
				System.out.println(sqlUpdate);

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
							}catch(Exception e){                        }
						}
					}
					while (rs.next()){
						System.out.println(rs);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return o;
	}

	public void init(){
		try { Class.forName("com.mysql.jdbc.Driver").newInstance(); }
		catch (Exception e) { e.printStackTrace(); }
		try { 
			this.connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/java2e","root","") ;
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
			}else if (name.equals("Boolean") || name.equals("boolean")){
				return TYPE_FIELD_BOOLEAN;
			}else if (name.equals("Float") || name.equals("float")){
				return TYPE_FIELD_FLOAT;
			}
		}
		return null;
	}

}