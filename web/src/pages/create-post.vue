<template>
    <div class="container">
        <div class="row">
            <div class="col-lg-6 mx-auto p-3 rounded-3 bg-opacity-75 card shadow">
                <h4 class="text-center">Create New Post</h4>

                <div class="alert alert-danger my-3" v-if="error">
                    Invalid credentials
                </div>

                <form @submit.prevent="submit" class="mt-4 row">
                    <label class="form-group col-12">
                        Title
                        <input type="text" v-model="title" class="form-control">
                    </label>
                    <label class="form-group col-12 mt-4">
                        description
                        <textarea v-model="description" class="form-control" cols="30" rows="10"></textarea>
                    </label>
                    <label class="form-group col-12 mt-4">
                        Image
                        <input type="file" class="form-control" accept="image/*" @change="changeFile">
                    </label>
                    <div class="d-flex justify-content-end align-items-end">
                        <button v-if="!loading" type="submit" class="mt-4 btn btn-success">
                            Submit
                        </button>
                        <button v-else type="submit" class="mt-4 btn btn-success disabled">
                            Please wait...
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "create-post",
    data: () => ({
        title: '',
        description: "",
        image: null,
        loading: false,
        error: false,
    }),
    methods: {
        submit() {
            this.loading = true
            const formData = new FormData()

            formData.append("title", this.title)
            formData.append("description", this.description)
            formData.append("image", this.image)
            formData.append("token", this.$cookies.get("token"))

            this.$http.post("/create-post", formData).then(
                (res) => {
                    this.$router.push("/dashboard")
                }
            ).catch(
                (err) => {
                    console.log(err)
                    this.error = true
                    this.loading = false
                }
            )
        },
        changeFile(e) {
            if (!e.target.files || e.target.files.length === 0)
                return

            this.image = e.target.files[0]

        }
    }
}
</script>

<style scoped>

</style>