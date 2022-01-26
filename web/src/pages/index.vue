<template>
    <div class="container">
        <div class="row">
            <div class="mx-auto col-xl-8 p-3">

            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "index",
    data: () => ({
        postData: []
    }),
    methods: {
        getData() {
            this.$http.get("/new-posts").then(
                (res) => {
                    this.postData = res.data
                }
            ).catch(
                (res) => {

                }
            )
        }
    },
    mounted() {

    }
}
</script>

<style scoped>

</style>