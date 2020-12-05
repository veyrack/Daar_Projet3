<template>
<div class="d-flex justify-center">
  <v-container fill-height>
    <v-row>
      <v-col cols="12" lg="5">
        <v-row>
          <h3>Recherche par Nom et/ou Prénom</h3>
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
        <v-row>
          <h3>Recherche par Tags</h3>
        </v-row>
        <v-row>
          <v-col>
              <TagSelector label="Choisir les compétences" :itemslist="programmingLanguages" v-model="selected"/>
          </v-col>
          <v-col>
              <TagSelector label="Choisir les niveaux d'étude" :itemslist="degrees" v-model="selected"/>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <v-sheet
              elevation="10"
              rounded="xl"
            >
              <v-sheet
                class="primary"
              >
                <v-container class="pa-0">
                  <v-row>
                    <v-col class="ml-4 d-flex align-center">
                      <h3 class="white--text">
                        Filtres selctionnés : {{selected.length}}
                      </h3>
                    </v-col>
                    <v-col class="text-right">
                      <v-btn
                        elevation="4"
                        medium
                        dark
                        rounded
                        class="mr-3"
                      >
                        <div class="mr-2">
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
      <v-col cols=12 lg="7">
        <ListeProfils/>
      </v-col>
    </v-row>
  </v-container>
</div>
</template>

<script>
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
        'Java', 'Python', 'C', 'C++', 'C#',
        'Haskell', 'Rust', 'Go', 'Fortran',
        'NodeJs', 'PHP'
      ],
      degrees: [
        'bac', 'bac+2', 'bac+3', 'bac+5',
        'bac+8', 'Bts', 'Dut', 'Licence',
        'Master', 'Doctorat'
      ]
    }
  },
  components: {
    TagSelector,
    ListeProfils
  }
}

</script>
