<template>
    <div>
        <nav class="navbar navbar-expand-lg navbar-light bg-light shadow">
            <div class="container-fluid">
                <router-link exact-active-class="" to="/">
                    <img src="/instagram.png" class="mx-4" alt="logo">
                </router-link>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <router-link class="nav-link" exact-active-class="active" to="/posts">New Posts</router-link>
                        </li>
                    </ul>
                    <router-link to="/login" exact-active-class="active" tag="button" class="btn btn-outline-primary">
                        Login
                    </router-link>
                    <router-link class="btn btn-outline-success mx-2" exact-active-class="active" to="/register" tag="button">
                        Register
                    </router-link>
                </div>
            </div>
        </nav>
        <main class="mt-5">
            <router-view>

            </router-view>
        </main>
    </div>
</template>

<script>

</script>



<style>
body {
    width: 100vw;
    height: 100vh;
    background: #f2f2f2;
}

.active {
    background: #ddd;
    border-radius: 4px;
}
</style>