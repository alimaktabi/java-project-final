<template>
    <div class="container">
        <h3>New Posts</h3>
        <div class="row">
            <div class="mx-auto col-xl-10 ">
                <router-link :to="`/pages/page/${post.username}/posts/${i}`" class="m-3 p-4 card d-flex"
                             v-for="(post, i) in postData" :key="i"
                >
                    <div>
                        <h3>
                            {{ post.title }}
                        </h3>
                        <div>
                            <small>
                                Caption:
                            </small>
                            <p>
                                {{ post.description }}
                            </p>
                        </div>
                    </div>

                    <img :src="post.image" height="400" :alt="post.title">

                    <div v-if="post.isLiked" @click.prevent="() => like(i)" class="btn btn-default mt-4">
                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">

                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                        </svg>
                    </div>

                    <div v-else @click.prevent="() => unLike(i)" class="btn btn-default mt-4">
                        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-star-fill border-3" viewBox="0 0 16 16">

                            <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                        </svg>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "index",
        data: () => ({
            postData: [],
            loading: true,
        }),
        methods: {
            getData() {
                this.loading = true
                this.$http.get("/new-posts").then(
                    (res) => {
                        console.log(res)
                        this.postData = res.data
                        this.loading = false
                    }
                ).catch(
                    (res) => {

                    }
                )
            },
            like(i) {

                const data = this.postData[i]

                this.$http.get("/like-post?username=" + data.username + "&id=" + i).then(
                    (res) => {
                        this.postData[i].isLiked = true
                        this.postData = [...this.postData]
                    }
                )
            },
            unLike(i) {

                const data = this.postData[i]

                this.$http.get("/unlike-post?username=" + data.username + "&id=" + i).then(
                    (res) => {
                        this.postData[i].isLiked = false
                        this.postData = [...this.postData]
                    }
                )
            }
        },
        mounted() {
            this.getData()
        }
    }
</script>

<style scoped>
    img {
        max-width: 500px;
        object-fit: cover;
    }

    a {
        color: inherit;
        text-decoration: none;
    }

    svg:hover {
        color: #0a53be;
    }
</style>