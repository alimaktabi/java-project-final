<template>
    <div class="text-center">
        <label>
            <input placeholder="Search" v-model.lazy="search" type="text" class="form-control">
        </label>

        <div class="mt-4">
            <span v-if="loading" class="text-center alert alert-info">Loading...</span>
        </div>
    </div>
</template>

<script>
export default {
    name: "Search",
    data: () => ({
        search: "",
        loading: false,
        res: []
    }),

    methods: {
    },
    watch: {
        search(val) {
            if (val.length < 3)
                return

            this.loading = true

            this.$http.get(`/search?q=${val}`).then(
                (res) => {
                    this.res = res.data.properties
                    this.loading = false
                }
            )
        }
    }
}
</script>

<style scoped>

</style>