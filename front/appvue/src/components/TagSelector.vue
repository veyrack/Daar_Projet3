<template>
    <v-select
        v-model="innerValue"
        :items="itemslist"
        @change="emitChange"
        :menu-props="{ maxHeight: '600' }"
        :label="label"
        multiple
        deletable-chips
        chips
        hide-selected
        persistent-hint
    >
        <template v-slot:selection="{index}">
        <v-chip v-if="index === 0">
            <span>{{ itemsContains(itemslist, value) }} selections</span>
        </v-chip>
        </template>
    </v-select>
</template>

<script>
export default {
    name: "TagSelector",
    props: {
        label: String,
        itemslist: Array,
        value: Array
    },
    data () {
        return {
            innerValue: this.value
        }
    },
    methods: {
        emitChange(inputValue) {
            // "input" for parent v-model 
            this.$emit("input", inputValue)
        },
        itemsContains(list1, list2) {
            let sum = 0;
            for (var i in list1) {
                sum += (list2.indexOf(list1[i])<0) ? 0 : 1;
            }
            return sum;
        }
    },
    watch: {
        value() {
            this.innerValue = this.value;
        }
    }
}
</script>