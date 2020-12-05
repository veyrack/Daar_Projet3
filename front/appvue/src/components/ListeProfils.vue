<template>
  <div>
    <v-responsive
      max-width="400"
      class="mx-auto mb-4"
    >
      <v-text-field
        v-model="recherche"
        label="Recherche Nom"
      ></v-text-field>
    </v-responsive>

    <v-card
      elevation="16"
      max-width="800"
      class="mx-auto pb-2 pt-2 pl-2"
    >
      <v-virtual-scroll
        :items="items"
        height="600"
        item-height="64"
      >
        <template v-slot:default="{ item }">
          <v-list-item :key="item">
            <v-list-item-action>
              <v-btn
                fab
                small
                depressed
                color="primary"
              >
                {{ item }}
              </v-btn>
            </v-list-item-action>

            <v-list-item-content>
              <v-list-item-title>
                CV <strong>ID {{ item }} </strong> nom prénom
              </v-list-item-title>
            </v-list-item-content>

            <v-list-item-action>
              <v-icon small>
                mdi-open-in-new
              </v-icon>
            </v-list-item-action>
          </v-list-item>

          <v-divider></v-divider>
        </template>
      </v-virtual-scroll>
    </v-card>
  </div>
</template>

<script>

export default {
    name: 'ListeProfils',
    data: function() {
        return {
            recherche: ""
        }
    },
    props: {
        label: String,
        itemslist: Array,
        value: Array
    },
    computed: {
        items () {
            return Array.from({ length: this.length }, (k, v) => v + 1)
      },
      length () {
          return 7000
      },
    },
}
</script>