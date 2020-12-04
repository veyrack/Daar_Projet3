<template>
  <div id="placement">
    <form >
      <div id="label">
        <h3><label for="file">Ajouter un CV</label></h3>
      </div>
      <input type="file" id="file" accept=".pdf" />
      <button v-on:click="submitFile()" type="submit" name="envoyer" >Envoyer</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Accueil',
  data: function () {
       return{
        prenom: "",
        nom: "",
        filtre: "",
        profils: [],
        listeCV :[]
       }
 },
methods: {
        filter: function(filtre){
        //console.log('http://localhost:8080/CV?filtre='+filtre)
        axios
        .get('http://localhost:8080/CV?filtre='+filtre)
        .then(response => (this.listeCV = response.data))
        },
        getcv: function(){
        axios
        .get('http://localhost:8080/CV')
        .then(response => (this.listeCV = response.data.content))
        },
        //Envoie le fichier pdf au serveur
        submitFile(){
          

          let formData = new FormData();

          //get pdf 
          let file = document.getElementById("file")

          if(file!=null){

            //Add the form data we need to submit
            formData.append('file', file.files[0]); // C'est toujours file.files[0] pour récupérer un fichier

            //Make the request to the POST /single-file URL
            axios.post( 'http://localhost:8080/CV/file', //Ici c'est 8080 mais ca peut être différent en fonction du user
                      formData,
                      {
                      headers: {
                          'Content-Type': 'multipart/form-data'
                      }
            }).then(function(){
              console.log('SUCCESS!!');
             })
            .catch(function(p){
              console.log('FAILURE!!');
              console.log(p.response)
            });
          }
          else
            console.log("nope")
        }
        
}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#placement {
  margin-top: 10%;
  margin-left: 30%;
}
</style>
