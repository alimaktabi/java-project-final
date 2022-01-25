<template>
    <div class="container">
        <div class="row">
            <div class="col-lg-6 mx-auto p-3 rounded-3 bg-opacity-75 card shadow">
                <h4 class="text-center">Instagram | Register</h4>

                <form class="mt-4 row">
                    <label class="form-group col-6 mt-4">
                        First Name
                        <input type="text" class="form-control">
                    </label>
                    <label class="form-group col-6 mt-4">
                        Last Name
                        <input type="text" class="form-control">
                    </label>
                    <label class="form-group col-12">
                        Username
                        <input type="text" class="form-control">
                    </label>
                    <label class="form-group col-12 mt-4">
                        Password
                        <input type="password" class="form-control">
                    </label>
                    <label class="form-group col-12 mt-4">
                        Password Confirmation
                        <input type="password" class="form-control">
                    </label>
                    <div class="d-flex justify-content-between align-items-end">
                        <small>
                            Already have an account ?
                            <router-link to="/login">Login</router-link>
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
    name: "Register",
    data: () => ({
        loading: false,
        username: "",
        password: ""
    })
}
</script>

<style scoped>
input {
    margin-top: 10px;
}
</style>