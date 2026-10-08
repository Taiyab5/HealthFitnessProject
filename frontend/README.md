# HealthFitness React frontend

## Local development

From this directory, install dependencies and start Vite:

```sh
npm install
npm run dev
```

The Vite development server proxies `/api` to `http://localhost:8080/healthfitness-api`.
Start the Tomcat backend and MySQL first to use the registration and login forms.

## Checks

```sh
npm run lint
npm run build
```

## Vercel

Set the Vercel project root to `frontend`. Vercel uses the Vite build output in `dist`; `vercel.json` directs app routes to the React entry point.

The login and registration forms use the Tomcat API. Registration stores account details in MySQL; passwords are stored as salted PBKDF2 hashes.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Oxc](https://oxc.rs)
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/)

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.
