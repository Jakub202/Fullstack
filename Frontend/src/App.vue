<template>
  <header>
    <nav>
      <router-link to="/">Home</router-link>
      <router-link to="/contact">Contact</router-link>
      <router-link to="/Calculator">Calculator</router-link>
    </nav>
  </header>

  <router-view />
</template>

<style>
header {
  background-color: #333; /* Set background color */
  color: white; /* Set text color */
  height: 60px; /* Set height */
  display: flex; /* Set display to flex */
  justify-content: center; /* Center menu items horizontally */
  align-items: center; /* Center menu items vertically */
}

nav {
  display: flex; /* Set display to flex */
  justify-content: space-around; /* Distribute menu items evenly */
  align-items: center; /* Center menu items vertically */
  width: 50%; /* Set width of menu */
  height: 100%; /* Set height of menu */
  border-left: 1px solid white; /* Add a border to the left of menu */
  border-right: 1px solid white; /* Add a border to the right of menu */
}

nav a {
  text-decoration: none; /* Remove underline from links */
  color: white; /* Set text color */
  font-size: 1.2rem; /* Set font size */
  padding: 0.5rem; /* Add padding */
  border-left: 1px solid white; /* Add a border to the left of menu items */
}

nav a:first-child {
  border-left: none; /* Remove border from first menu item */
}

nav a:last-child {
  border-right: none; /* Remove border from last menu item */
}
</style>
