const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [{ path: "", component: () => import("pages/IndexPage.vue") }],
  },

  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
  {
    path: "/demo",
    name: "demo",
    component: () => import("../pages/DemoPage.vue"),
  },
  {
    path: "/add-new-employee",
    name: "add-new-employee",
    component: () => import("../pages/AddEmployeePage.vue"),
    props: (route) => ({
      jobs: route.params.jobs,
      departments: route.params.departments,
    }),
  },
];

export default routes;
