<template>
<div class="d-flex justify-center">
  <v-container>
    <v-row>
      <v-col>
        <v-select
          v-model="selected"
          :items="programmingLanguage"
          :menu-props="{ maxHeight: '600' }"
          label="Choisir les compétences"
          multiple
          deletable-chips
          chips
          hide-selected
          persistent-hint
        >
          <template v-slot:selection="{index }">
            <v-chip v-if="index === 0">
              <span>{{ itemsContains(programmingLanguage, selected) }} selections</span>
            </v-chip>
          </template>
        </v-select>
      </v-col>

      <v-col>
        <v-select
          v-model="selected"
          :items="degrees"
          :menu-props="{ maxHeight: '600' }"
          label="Choisir les niveaux d'étude"
          multiple
          deletable-chips
          chips
          hide-selected
          persistent-hint
        >
          <template v-slot:selection="{index }">
            <v-chip v-if="index === 0">
              <span>{{ itemsContains(degrees, selected) }} selections</span>
            </v-chip>
          </template>
        </v-select>
      </v-col>
      <!-- <v-col>
        <SelectionItems label="test" :selectedtags="['testValue','testValue2']"/>
      </v-col> -->
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
                    Total de champs selectionnés : {{selected.length}}
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
  </v-container>
</div>
</template>

<script>
// import SelectionItems from "./SelectionItems";

export default {
  name: 'Recherche',
  data: function() {
    return {
      selected: [],
      programmingLanguage: [
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
    // SelectionItems
  },
  methods: {
    itemsContains(list1, list2) {
      let sum = 0;
      for (var i in list1) {
        sum += (list2.indexOf(list1[i])<0) ? 0 : 1;
      }
      return sum;
    }
  }
}

</script>
