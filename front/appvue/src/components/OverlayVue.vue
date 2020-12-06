<template>
  <v-card
    max-width="375"
    class="mx-auto"
    light
  >
    <v-img
      src="@/assets/wallpaperOverlay.jpg"
      height="300px"
    >
      <v-row class="fill-height">
        <v-card-title 
            class="white--text d-flex align-center flex-column justify-space-between"
            style="width:100%; padding-bottom:0;"
        >
            <div class="align-self-start">
                <v-btn
                    dark
                    icon
                    class="ml-4"
                >
                    <v-icon>mdi-pencil</v-icon>
                </v-btn>

                <v-btn
                    dark
                    icon
                >
                    <v-icon>mdi-dots-vertical</v-icon>
                </v-btn>
            </div>
            <div
                class="display-1 card-title"
            >
                {{ item.nom }} {{ item.prenom }}
            </div>
        </v-card-title>
      </v-row>
    </v-img>

    <v-list two-line>
      <v-list-item>
        <v-list-item-icon>
          <v-icon color="indigo">
            mdi-phone
          </v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title> {{ item.numTel }} </v-list-item-title>
          <v-list-item-subtitle>Numéro de téléphone</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider inset></v-divider>

      <v-list-item>
        <v-list-item-icon>
          <v-icon color="indigo">
            mdi-email
          </v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title> {{ item.email }} </v-list-item-title>
          <v-list-item-subtitle>Mail personnel</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider inset></v-divider>

      <v-list-item>
        <v-list-item-icon>
          <v-icon color="indigo">
            mdi-lightbulb-on-outline
          </v-icon>
        </v-list-item-icon>

        <v-list-item-content>
          <v-list-item-title>Compétences</v-list-item-title>
          <v-list-item-subtitle> {{ item.competences ? item.competences.join(", ") : '' }} </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script>
export default {
    name: "OverlayVue",
    props: {
        item: Object
    },
}
</script>

<style lang="scss">
    .card-title {
        background: linear-gradient(360deg, rgba(0,0,0,0.773546918767507) 20%, rgba(0,0,0,0) 100%);
        width: 100%;
        text-align:center;
        padding: 10px 0;
    }
</style>