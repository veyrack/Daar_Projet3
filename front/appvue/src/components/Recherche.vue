<template>
<div class="d-flex justify-center">
  <v-container>
    <v-row>
      <v-col cols="5" lg="4">
        <v-card class="pa-4">
          <v-row>
            <h3 class="ml-2 mr-2">Recherche par Nom et/ou Prénom</h3>
            <v-btn
              color="primary"
              class="mx-0" fab x-small
              :disabled="nom.length+prenom.length==0"
              @click="profilsNameRecherche">
              <v-icon dark>
                mdi-arrow-right-box
              </v-icon>
            </v-btn>
          </v-row>
          <v-row>
            <v-col>
              <v-text-field
                v-model="nom"
                label="Nom"
                required
              ></v-text-field>
            </v-col>
            <v-col>
              <v-text-field
                v-model="prenom"
                label="Prénom"
                required
              ></v-text-field>
            </v-col>
          </v-row>
        </v-card>
        <v-card class="pa-4 mt-4">
          <v-row>
            <h3 class="ml-2">Recherche par Filtres</h3>
          </v-row>
          <v-row>
            <v-col>
                <TagSelector label="Choisir les compétences" :itemslist="programmingLanguages" v-model="selected"/>
            </v-col>
            <v-col>
                <TagSelector label="Choisir les niveaux d'étude" :itemslist="degrees" v-model="selected"/>
            </v-col>
          </v-row>
        </v-card>
        <v-row>
          <v-col>
            <v-sheet
              elevation="10"
              rounded="xl"
            >
              <v-sheet
                class="primary"
              >
                <v-container class="py-0">
                  <v-row>
                    <v-col class="ml-1 d-flex align-center">
                      <h3 class="white--text">
                        Filtres : {{selected.length}}
                      </h3>
                      <v-btn
                        class="ml-3"
                        small
                        dark
                        fab
                        v-if="selected.length>0"
                        @click="selected=[]"
                      >
                        <v-icon>
                          mdi-trash-can
                        </v-icon>
                      </v-btn>
                    </v-col>
                    <v-col class="text-right">
                      <v-btn
                        elevation="4"
                        medium
                        dark
                        rounded
                        @click="filtrageRecherche"
                      >
                        <div class="mr-1">
                        Rechercher
                        </div>
                        <v-icon class="mdi-24px pr-0">
                          mdi-file-search-outline
                        </v-icon>
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-container>
              </v-sheet>

              <div class="pa-10">
                <v-chip-group
                  active-class="primary--text"
                  column
                >
                  <v-chip
                    v-for="(tag, index) in selected"
                    close
                    @click:close="selected.splice(index,1)"
                    :key="tag"
                  >
                    {{ tag }}
                  </v-chip>
                </v-chip-group>
              </div>
            </v-sheet>
          </v-col>
        </v-row>
      </v-col>
      <v-col cols="7" offset-lg="1">
        <ListeProfils :listProfils="response"/>
      </v-col>
    </v-row>
  </v-container>
</div>
</template>

<script>
import axios from 'axios'

import TagSelector from "./TagSelector";
import ListeProfils from "./ListeProfils";

export default {
  name: 'Recherche',
  data: function() {
    return {
      prenom: "",
      nom: "",
      selected: [],
      programmingLanguages: [
        'c','c#','c++','caml','clojure', 'cofeescript',
        'css','dotnet','go','groovy','hadoop', 'haskell',
        'html','java','javascript','lua','matlab', 'nosql',
        'objectivec','ocaml', 'perl','php','python','r',
        'ruby', 'rust', 'scala', 'shell', 'spark', 'sql', 
        'swift', 'typescript', 'uml', 'vba',
      ],
      degrees: [
        'Baccalauréat', 'Bts', 'Dut', 'Licence',
        'Master', 'Doctorat', 'Ecole'
      ],
      response: [],
    }
  },
  methods: {
    profilsNameRecherche(){
      if(this.nom.length==0){
        this.getByName("prenom", this.prenom)
      }
      else if(this.prenom.length==0){
        this.getByName("nom", this.nom)
      } 
      else {
        axios
        .get('http://localhost:8080/CV?nom='+this.nom+"&prenom="+this.prenom)
        .then(response => (this.response = response.data))
        .catch((p) => {
          console.log(p)
        });
      }
    },
    filtrageRecherche(){
      if (this.selected.length==0){
        this.getAllCv()
      } else {
        this.filter()
      }
    },
    getByName: function(nameType, name){
      axios
      .get('http://localhost:8080/CV?'+nameType+"="+name)
      .then(response => (this.response = response.data))
      .catch((p) => {
        console.log(p)
      });
    },
    getAllCv: function(){
      axios
      .get('http://localhost:8080/CV')
      .then(response => (this.response = response.data.content))
      .catch((p) => {
        console.log(p)
      });
    },
    filter: function(){
      var arg = this.wrapFiltrage()
      console.log(arg)
      axios
      .get('http://localhost:8080/CV?filtres='+arg.join(','))
      .then(response => (this.response = response.data))
      .catch((p) => {
        console.log(p)
      });
    },
    wrapFiltrage(){
      var listRes = this.selected;
      for(var i=0; i<listRes.length; i++){
        switch(listRes[i]){
          case "c++":
            listRes[i]="cpp"
            break;
          case "c#":
            listRes[i]="csharp"
            break;
          default:
        }
      }
      return listRes;
    }
  },
  components: {
    TagSelector,
    ListeProfils
  }
}

</script>
