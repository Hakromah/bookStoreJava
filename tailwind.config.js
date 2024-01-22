/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
      "./src/main/resources/templates/**/*.html"
  ],
  theme: {
    extend: {
      backgroundColor: ['responsive', 'hover', 'focus', 'group-hover'],
      textColor: ['responsive', 'hover', 'focus', 'group-hover'],
    },
  },
  plugins: [],
}

