<template >
  <div class="d-flex justify-center align-center" style="height:100%;">
    <v-card width="50%">
      <v-form 
        v-model="valid"
        ref="formcv"
      >
        <v-card-text class="pb-0">

          <div>
            <h2 class="mb-6" style="text-align:center;">Ajouter un CV</h2>
          </div>

          <v-text-field
            v-model="nom"
            :rules="nameRule"
            label="Nom"
            required
          ></v-text-field>
          
          <v-text-field
            v-model="prenom"
            :rules="nameRule"
            label="Prénom"
            required
          ></v-text-field>

          <v-text-field
            v-model="tel"
            :rules="phoneRule"
            label="Numéro de téléphone"
            required
          ></v-text-field>

          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="E-mail"
            type="email"
            required
          ></v-text-field>

          <!-- <input type="file" id="file" accept=".pdf" />
          <button v-on:click="submitFile()" type="submit" name="envoyer" >Envoyer</button> -->
          
          <v-file-input
            v-model="fileInput"
            accept=".pdf"
            color="primary"
            placeholder="Selectionner votre fichier au format pdf"
            prepend-icon="mdi-paperclip"
            :show-size="1000"
            :rules="fileRules"
          >
            <template v-slot:selection="{text}">
              <v-chip
                class="text--darken-3 mx-2"
                color="primary"
                dark
                label
                small
              >
                {{ text }}
              </v-chip>
            </template>
          </v-file-input>

          <v-checkbox
            class="mt-1"
            :rules="conditionRule"
            label="Accepter les conditions ?"
            required
          ></v-checkbox>
          
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="error"
            class="mr-2"
            @click="reset"
          >
            Réinitialiser
          </v-btn>
          <v-btn
            color="success"
            :disabled="!valid"
            @click="submitFile"
          >
              Envoyer
          </v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Accueil',
  data: function () {
    return {
      fileInput: [],
      nom: "",
      prenom: "",
      email: "",
      tel: "",
      nameRule: [
        v => !! v || 'Veuillez remplir ce champ !'
      ],
      emailRules: [
        v => !! v || 'E-mail requis',
        v => /.+@.+\..+/.test(v) || 'E-mail doit etre valide',
      ],
      phoneRule: [
        v => !! v || "Numéro de téléphone requis",
        v => /^(?:(?:\+|00)33|0)\s*[1-9](?:[\s.-]*\d{2}){4}$/.test(v) || "Numéro invalide",
      ],
      fileRules: [
        v => !!v || 'Le CV au format pdf est nécessaire',
      ],
      conditionRule: [
        v => !!v || 'Vous devez accepter les conditions pour envoyer !'
      ],
      valid: true
    }
 },
methods: {
  filter: function(filtre){
  //console.log('http://localhost:8080/CV?filtre='+filtre)
  axios
  .get('http://localhost:8080/CV?filtre='+filtre)
  .then(response => (this.listeCV = response.data))
  },

  // Recupere les cv
  getcv: function(){
  axios
  .get('http://localhost:8080/CV')
  .then(response => (this.listeCV = response.data.content))
  },

  // Methodes du formulaire
  validate () {
    this.$refs.formcv.validate()
  },
  reset () {
    this.$refs.formcv.reset()
  },

  //Envoie le fichier pdf au serveur
  submitFile(){
    
    let formData = new FormData();

    if(this.fileInput!=null){

      //Add the form data we need to submit
      formData.append('file', this.fileInput);
      formData.append('prenom', this.prenom);
      formData.append('nom', this.nom);
      formData.append('mail', this.email);
      formData.append('tel', this.tel);

      console.log(formData);

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
