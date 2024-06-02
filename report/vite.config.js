import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import vitePluginRequire from 'vite-plugin-require'
import ViteRequireContext from '@originjs/vite-plugin-require-context'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react(), ViteRequireContext.default()],
})
