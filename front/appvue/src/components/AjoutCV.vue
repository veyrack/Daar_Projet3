<template >
  <div class="d-flex justify-center align-center" style="height:100%;">
    
    <v-card width="50%" elevation="24" class="pa-4">
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
       
          <v-file-input
            v-model="fileInput"
            accept=".pdf"
            color="primary"
            placeholder="Selectionner Cv au format pdf < 5 Mb"
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
    <v-snackbar
      v-model="snackbar"
      :color="notifColor"
      elevation="24"
      :timeout="-1"
    >
      {{ text }}
      <template v-slot:action="{ attrs }">
        <v-btn
          text
          icon
          v-bind="attrs"
          @click="snackbar = false"
        >
          <v-icon class="mdi-24px pr-0">
            mdi-close-box
          </v-icon>
        </v-btn>
      </template>
    </v-snackbar>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'AjoutCV',
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
        v => !! v || 'Le CV au format pdf est nécessaire',
        v => {
            let size = 0;
            if (v!=null) {
              size = v.size;
            }
            return size < 5000000 || 'Fichier trop volumineux > 10 Mb'
          }
      ],
      valid: true,
      snackbar: false,
      notifColor: "",
      text: ""
    }
  },
  methods: {
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

      //Add the form data we need to submit
      formData.append('file', this.fileInput);
      formData.append('prenom', this.prenom);
      formData.append('nom', this.nom);
      formData.append('mail', this.email);
      formData.append('tel', this.tel);

      console.log(this.fileInput);

      //Make the request to the POST /single-file URL
      axios.post( 'http://localhost:8080/CV/file', //Ici c'est 8080 mais ca peut être différent en fonction du user
        formData,
        {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
      }).then((p) => {
        this.notifColor="success"
        this.text="Upload Réussi";
        this.snackbar=true;
        console.log(p);
        })
      .catch((p) => {
        this.notifColor="error"
        this.text="Erreur d'upload";
        this.snackbar=true;
        console.log(p.response);
      });
    }
  }
}
</script>
