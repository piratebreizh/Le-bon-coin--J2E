<script>
	document.querySelector('#file').onchange = function() {
    alert(this.files[0].name);
};
</script>