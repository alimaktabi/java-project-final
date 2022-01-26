<template>
    <div class="container">
        <div class="mb-4 text-center">
        <span class="alert alert-info" v-if="userData.followed && !userData.isFollowing">
            {{ userData.username }} is Following you
        </span>
        </div>
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
            <button @click="follow" :disabled="loading" class="btn btn-outline-primary" v-if="!userData.isFollowing">
                + Follow
            </button>
            <button @click="unfollow" :disabled="loading" v-else class="btn btn-outline-warning">
                - UnFollow
            </button>
        </header>

        <main class="mt-5">
            <hr>
            <h4>Posts</h4>

            <div>
                <div class="alert alert-info" v-if="!userData.posts || userData.posts.length === 0">
                    No posts published yet
                </div>

                <div class="row mt-5 gap-5" v-else>
                    <router-link :to="`/pages/page/${userData.username}/posts/${i}`" class="col-md-3"
                                 v-for="(post, i) in userData.posts" :key="i">
                        <img :src="post.image" width="200" height="300" alt="">
                    </router-link>
                </div>
            </div>

        </main>
    </div>
</template>

<script>
    export default {
        name: "Page",
        data: () => ({
            userData: {},
            loading: false
        }),
        methods: {
            follow() {
                this.loading = true
                const formData = new FormData()

                formData.append("username", this.userData.username)

                this.$http.post("/follow", formData).then(
                    (res) => {
                        this.loading = false
                        this.userData.isFollowing = true
                        this.userData.followers++;
                    }
                )
            },
            unfollow() {
                this.loading = true

                const formData = new FormData()

                formData.append("username", this.userData.username)

                this.$http.post("/unfollow", formData).then(
                    (res) => {
                        this.loading = false
                        this.userData.isFollowing = false
                        this.userData.followers--;
                    }
                )
            },
            getDashboardData() {

                this.$http.get("/get-page?username=" + this.$route.params.username).then(
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