<template>
    <div class="container">
        <div class="row">
            <div class="col-lg-6 mx-auto p-3 rounded-3 bg-opacity-75 card shadow">
                <h4 class="text-center">Edit Profile</h4>

                <div class="alert alert-danger my-3" v-if="error">
                    Username already taken
                </div>

                <form @submit.prevent="setData" class="mt-4 row">
                    <label class="form-group col-12 mt-4">
                        Full Name
                        <input type="text" v-model="fullName" class="form-control">
                    </label>
                    <label class="form-group col-12">
                        Username
                        <input type="text" v-model="username" class="form-control">
                    </label>
                    <div class="d-flex justify-content-end align-items-end">
                        <button v-if="!loading" type="submit" class="mt-4 btn btn-success">
                            Update
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
    name: "edit-profile",
    data: () => ({
        loading: true,
        username: "",
        fullName: "",
        error: false,
    }),
    methods: {
        getData() {
            const formData = new FormData()
            formData.append("token", this.$cookies.get("token"))

            this.$http.post("/get-profile", formData).then(
                (res) => {
                    this.loading = false
                    const { data } = res
                    this.username = data.username
                    this.fullName = data.fullName
                }
            )
        },
        setData() {
            const formData = new FormData()
            formData.append("token", this.$cookies.get('token'))
            formData.append("fullName", this.fullName)
            formData.append("username", this.username)
            this.loading = true

            this.$http.post("/update-profile", formData).then(
                (res) => this.$router.push("/dashboard")
            ).catch(
                (err) => {
                    this.error = true
                    this.loading = false
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
input {
    margin-top: 10px;
}
</style>

