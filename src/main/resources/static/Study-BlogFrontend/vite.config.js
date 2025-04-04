import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  test: {
    globals: true,        // enables global test functions like 'test', 'expect'
    environment: 'jsdom', // this sets the environment to jsdom, needed for React tests
    transformMode: {
      web: [/\.jsx$/, /\.tsx$/, /\.js$/, /\.ts$/], // make sure js/ts files are transformed
    },
  },
  server: {
    host: true,
    port: 5173, // vagy amit szeretnél
  },
});
