<template>
  <div>
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">
        <img class="navbar-brand" src="../assets/bear.png"  width="30" height="40">
    </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
         <router-link class="nav-link" to="/">AjouterCV</router-link>
      </li>
      <li class="nav-item active">
        <router-link class="nav-link" to="/recherche">Recherche</router-link>
      </li>
    </ul>
  </div>
</nav>
  </div>
</template>

<script>

export default {
  name: 'NavBar',
  props: {
    
  },
  methods: {
      changeComponent(){
        this.changeComponent
    }

 }
 

}
</script>