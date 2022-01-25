<template>
    <div class="container">
        <div class="row">
            <div class="col-lg-6 mx-auto p-3 rounded-3 bg-opacity-75 card shadow">
                <h4 class="text-center">Instagram | Login</h4>

                <div class="alert alert-danger my-3" v-if="error">
                    Invalid credentials
                </div>

                <form @submit.prevent="tryLogin" class="mt-4 row">
                    <label class="form-group col-12">
                        Username
                        <input type="text" v-model="username" class="form-control">
                    </label>
                    <label class="form-group col-12 mt-4">
                        Password
                        <input v-model="password" type="password" class="form-control">
                    </label>
                    <div class="d-flex justify-content-between align-items-end">
                        <small>
                            Don't have an account?
                            <router-link to="/register">Register</router-link>
                        </small>
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
    name: "Login",
    data: () => ({
        loading: false,
        username: "",
        password: "",
        error: false
    }),
    methods: {
        tryLogin(form) {
            this.loading = true
            this.error = false

            const formData = new FormData()

            if (this.username)
                formData.append("username", this.username)
            if (this.password)
                formData.append("password", this.password)

            this.$http.post("/login", formData).then(
                (res) => {
                    console.log(res)
                    this.$router.push("/dashboard")
                    this.loading = true
                }
            ).catch(
                (err) => {
                    this.error = true
                    this.loading = false
                }
            )

        }
    }
}
</script>

<style scoped>
input {
    margin-top: 10px;
}
</style>