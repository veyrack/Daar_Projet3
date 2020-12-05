<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
    >
      <v-row
        align="center"
      >
        <v-img 
          src="@/assets/iconCV.webp"
          class="ml-2"
          max-height="30"
          max-width="30"
        >
        </v-img>

        <v-btn text class="mr-2 ml-2"
          to="/ajoutcv" color="white"
        >
          Ajouter CV
        </v-btn>

        <v-btn text
          to="/recherche" color="white"
        >
          Recherche
        </v-btn>
      </v-row>
    </v-app-bar>
    <v-main>
      <v-container style="height:100%;">
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  components: {
  },

  data: () => ({
  }),
};
</script>
