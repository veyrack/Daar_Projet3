<template>
    <v-select
        v-model="selectedtags"
        :items="itemslist"
        :menu-props="{ maxHeight: '600' }"
        label="Choisir les niveaux d'étude"
        multiple
        deletable-chips
        chips
        hide-selected
        persistent-hint
    >
        <template v-slot:selection="{index}">
        <v-chip v-if="index === 0">
            <span>{{ itemsContains(itemslist, selectedtags) }} selections</span>
        </v-chip>
        </template>
    </v-select>
</template>

<script>
export default {
    name: "SelectionItems",
    // props: {
    //     label: String,
    //     itemslist: Array,
    //     selectedtags: Array,
    // }
}
</script>