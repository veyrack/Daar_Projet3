<template>
  <div>
    <v-card
      height="600"
      max-width="800"
      elevation="16"
      class="mx-auto pb-2 pt-2 pl-2 justify-center"
      v-if="listProfils==null || listProfils.length==0"
    >
      <v-card-text 
        style="width:100%;"
        class="fill-height d-flex align-center flex-column justify-center"
      >
        <h1> Recherche actuelle vide </h1>
        <p/> <p/> <p/>
        <h3> Veuillez effectuer une recherche </h3>
        <p/>
        <ol>
          <li> Soit par recherche de nom et ou prénom </li>
          <li> Soit par conjonction des filtrages</li>
        </ol>
        <p/> <p/> <p/>
        <h3> Rechercher sans filtre revient à retourner toute la base de CV</h3>
      </v-card-text>

    </v-card>
    <v-card
      elevation="16"
      max-width="800"
      class="mx-auto pb-2 pt-2 pl-2"
      v-if="listProfils != null && listProfils.length>0"
    >
      <v-virtual-scroll
        :items="listProfils"
        height="600"
        item-height="64"
      >
        <template v-slot:default="{ item }">
          <v-list-item :key="item.id">
            <v-list-item-action>
              <v-btn
                small
                :class="{'disable-events': true}"
                :color="'#'+strRGB(item.nom+item.prenom)"
              >
                {{ item.nom[0] }}{{ item.prenom[0] }}
              </v-btn>
            </v-list-item-action>

            <v-list-item-content>
              <v-list-item-title>
                <v-row>
                  <v-col>
                    <strong>
                      {{ item.nom }} {{item.prenom }}
                    </strong>
                  </v-col>
                  <v-col>
                    <div v-if="item.age!=null && item.age!=''">
                      {{ item.age }} ans
                    </div>
                  </v-col>
                </v-row>
              </v-list-item-title>
            </v-list-item-content>

            <v-list-item-action>
              <v-btn
                icon
                @click="showOverlay(item)"
              >
                <v-icon small>
                  mdi-open-in-new
                </v-icon>
              </v-btn>
            </v-list-item-action>
          </v-list-item>

          <v-divider></v-divider>
        </template>
      </v-virtual-scroll>
    </v-card>
    <v-overlay
      z-index="0"
      :value="overlay"
    >
      <v-row>
        <v-col class="pl-15">
          <OverlayVue 
            :item="selectedItem"
          />
        </v-col>
        <v-col>
          <v-btn
            class="white--text"
            icon
            @click="overlay = false"
          >
            <v-icon>
              mdi-close
            </v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-overlay>
  </div>
</template>

<script>

import OverlayVue from "./OverlayVue";

export default {
    name: 'ListeProfils',
    props: {
        listProfils: Array
    },
    components: {
      OverlayVue,
    },
    data: function() {
        return {
          overlay: false,
          selectedItem: null,
          data: [
            {id:"0", nom:"Lefevre", prenom:"Sébastien", age:"23", email:"seb.lef@hotmail.com", tel:"0102030405",
              competences: ["Python", "Java", "C", "VueJs"]},
            {id:"1", nom:"Brunet", prenom:"Maxence", age:"24", email:"max.brun@gmail.com", tel:"0605040201"},
            {id:"2", nom:"Lin", prenom:"Veyrack", age:"23", email:"Vey.Lin@coucou.com", tel:"0612345678", 
              competences: ["Java", "JavaSpring"]},
          ] 
        }
    },
    methods: {
      strRGB(value){
        var hash = 0;
        for (var i = 0; i < value.length; i++) {
          hash = value.charCodeAt(i) + ((hash << 5) - hash);
        }
        var c = (hash & 0x00FFFFFF)
            .toString(16)
            .toUpperCase();
        return "00000".substring(0, 6 - c.length) + c;
      },
      showOverlay(item){
        this.overlay = !this.overlay;
        this.selectedItem = item;
      }
    },
}
</script>

<style>
.disable-events {
  pointer-events: none
}
</style>