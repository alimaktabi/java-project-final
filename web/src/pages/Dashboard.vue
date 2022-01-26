<template>
    <div class="container">
        <header class="d-flex align-items-center mb-4">
            <img src="/loading.gif" class="profile-image" alt="profile-image">
            <div class="mx-5">
                <h6>{{ userData.username }}</h6>
                <small class="font-weight-bold">{{ userData.fullName }}</small>
            </div>

            <div class="mx-auto font-weight-bold d-flex justify-content-between">
                <h6 class="mx-4">
                    {{ userData.following }} Following
                </h6>
                <h6 class="mx-4">
                    {{ userData.followers }} Followers
                </h6>
            </div>
            <router-link tag="button" to="/create-post" class="btn btn-primary">
                New Post
            </router-link>
        </header>

        <main class="mt-5">
            <hr>
            <h4>Posts</h4>

            <div>
                <div class="alert alert-info" v-if="!userData.posts || userData.posts.length === 0">
                    No posts published yet
                </div>

                <div class="row mt-5 gap-5" v-else>
                    <router-link :to="`/pages/page/${userData.username}/posts/${i}`" class="col-md-3" v-for="(post, i) in userData.posts" :key="i">
                        <img :src="post.image" width="200" height="300" alt="">
                    </router-link>
                </div>
            </div>

        </main>
    </div>
</template>

<script>
export default {
    name: "Dashboard",
    data: () => ({
        userData: {}
    }),
    methods: {
        getDashboardData() {
            const formData = new FormData()

            formData.append("token", this.$cookies.get("token"))

            this.$http.post("/dashboard", formData).then(
                (res) => {
                    this.userData = res.data
                    console.log(res)
                }
            ).catch(
                (err) => console.log(err)
            )


        }
    },
    mounted() {
        this.getDashboardData()
    }
}
</script>

<style scoped>
.profile-image {
    width: 100px;
    height: 100px;
    border-radius: 100%;

}
</style>