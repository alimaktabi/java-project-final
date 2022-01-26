<template>
    <div class="container">
        <div class="row">
            <div class="p-4 card d-flex flex-row justify-content-between col-xl-10 mx-auto">
                <div class="d-flex flex-column justify-content-between">
                    <h3>
                        {{ userData.title }}
                    </h3>
                    <div>
                        <small>
                            Caption:
                        </small>
                        <p>
                            {{ userData.description }}
                        </p>
                    </div>
                </div>
                <div>
                    <img :src="userData.image" :alt="userData.title">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Post",
    data: () => ({
        userData: {}
    }),
    methods: {
        getPost() {
            this.$http.get("/get-post?username=" +
                this.$route.params.username +
                "&id=" +
                this.$route.params.id
            ).then(
                (res) => this.userData = res.data
            )
        }
    },
    created() {
        this.getPost()
    }
}
</script>

<style scoped>
img {
    max-width: 500px;
}
</style>