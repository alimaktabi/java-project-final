<template>
    <div class="text-center">
        <label>
            <input placeholder="Search" v-model.lazy="search" type="text" class="form-control form-control-lg">
        </label>

        <div class="mt-4 container">
            <span v-if="loading" class="text-center alert alert-info">Loading...</span>

            <span v-else-if="search && res.length === 0" class="text-center alert alert-warning">No result found</span>

            <div class="row flex-column">
                <router-link :to="`/pages/page/${item.username}`" v-for="(item, i) in res" :key="i" class="col-lg-4 p-2 mx-auto search-res rounded bg-white border-2 shadow-sm m-3">
                    <h4>{{ item.username }}</h4>
                    <p class="mb-0">{{ item.fullName }}</p>
                </router-link>
            </div>
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
                    console.log(res)
                    this.res = res.data
                    this.loading = false
                }
            )
        }
    }
}
</script>

<style scoped>
.search-res {
    color: inherit;
    text-decoration: none;
    transition: background-color 0.2s ease;
    cursor: pointer;
}
.search-res:hover {
    background-color: #b0b7fd !important;
}
</style>