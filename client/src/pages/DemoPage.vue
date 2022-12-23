<template>
  {{ employees }}
  {{ 123 }}
  <router-link to="/">Home</router-link>

  <div class="q-pa-xl">
    <div class="row q-col-gutter-xs">
      <div class="col-12 col-md-2">
        <q-btn
          label="SEARCH EMPLOYEES"
          class="full-width"
          @click="callGetEmployees"
        />
      </div>
      <div class="col-12 col-md-2">
        <q-btn
          label="SAVE EMPLOYEES"
          class="full-width"
          @click="moveToAddEmployee"
        />
      </div>
      <div class="col-12 col-md-2 offset-md-4">
        <q-btn
          label="UPDATE EMPLOYEES"
          class="full-width"
          @click="updateOpenDialog"
        />
      </div>
      <div class="col-12 col-md-2">
        <q-btn
          label="DELETE EMPLOYEES"
          class="full-width"
          @click="callDeleteEmployee"
        />
      </div>
    </div>
    <q-separator spaced />

    <div class="row q-col-gutter-xs">
      <div class="col-12 col-md-6">
        <q-input
          label="First name"
          filled
          clearable
          v-model="request.getEmployees.params.firstName"
        ></q-input>
      </div>
      <div class="col-12 col-md-6">
        <q-input
          label="last name"
          filled
          clearable
          v-model="request.getEmployees.params.lastName"
        ></q-input>
      </div>
      <div class="col-12 col-md-6">
        <q-input
          label="Email"
          filled
          clearable
          type="email"
          v-model="request.getEmployees.params.email"
        ></q-input>
      </div>
      <div class="col-12 col-md-6">
        <q-input
          label="Phone Number"
          filled
          clearable
          v-model="request.getEmployees.params.phoneNumber"
        ></q-input>
      </div>
      <div class="col-12 col-md-6">
        <q-input
          label="Hire Date From"
          filled
          clearable
          type="date"
          stack-label
          v-model="request.getEmployees.params.hireDateFrom"
        ></q-input>
      </div>
      <div class="col-12 col-md-6">
        <q-input
          label="Hire Date To"
          filled
          clearable
          type="date"
          stack-label
          v-model="request.getEmployees.params.hireDateTo"
        ></q-input>
      </div>
      <div class="col-12 col-md-6">
        <q-input
          label="Salary From"
          filled
          clearable
          type="number"
          v-model="request.getEmployees.params.salaryFrom"
        ></q-input>
      </div>
      <div class="col-12 col-md-6">
        <q-input
          label="Salary To"
          filled
          clearable
          type="number"
          v-model="request.getEmployees.params.salaryTo"
        ></q-input>
      </div>
      <div class="col-12 col-md-6">
        <q-select
          label="Job Title"
          filled
          clearable
          :options="jobs"
          map-options
          emit-value
          v-model="request.getEmployees.params.jobId"
        >
        </q-select>
      </div>
      <div class="col-12 col-md-6">
        <q-select
          label="Departmente Names"
          filled
          clearable
          :options="departments"
          map-option
          emit-value
          multiple
          v-model="departmentIds"
        >
        </q-select>
      </div>
    </div>
    <q-separator spaced />

    <div class="row">
      <div class="col">
        <q-table
          title="Employee"
          :rows="employees"
          :columns="columns"
          v-model:pagination="pagination"
          @request="alterPagination"
          row-key="employeeId"
          selection="single"
          v-model:selected="selected"
        >
          <template v-slot:body-cell-jobTitle="props">
            <q-td :props="props">
              {{ getLabel("jobs", props.row.jobId) }}
            </q-td>
          </template>
          <template v-slot:body-cell-departmentName="props">
            <q-td :props="props">
              {{ getLabel("departments", props.row.departmentId) }}
            </q-td>
          </template>
        </q-table>
      </div>
    </div>
  </div>

  <q-dialog v-model="editDialog" persistent>
    <q-card style="width: 700px; max-width: 80vw">
      <q-card-section>
        <span class="text-h6">Edit Employee Information</span>
      </q-card-section>
      <q-card-section>
        <q-input
          v-model="request.saveEmployee.data.employeeId"
          label="Employee ID"
          disable
          v-show="request.saveEmployee.data.employeeId"
        />
        <q-input
          v-model="request.saveEmployee.data.firstName"
          label="First Name"
          clearable
        />
        <q-input
          v-model="request.saveEmployee.data.lastName"
          label="Last Name"
          clearable
        />
        <q-input
          v-model="request.saveEmployee.data.email"
          label="Email"
          clearable
        />
        <q-input
          v-model="request.saveEmployee.data.phoneNumber"
          label="Phone Number"
          clearable
        />
        <q-select
          v-model="request.saveEmployee.data.jobId"
          label="Job Title"
          :options="jobs"
          emit-value
          map-options
          clearable
        />
        <q-select
          v-model="request.saveEmployee.data.departmentId"
          label="Department Name"
          :options="departments"
          emit-value
          map-options
          clearable
        />
        <q-input
          v-model="request.saveEmployee.data.hireDate"
          label="Hire Date"
          type="date"
          stack-label
          clearable
        />
        <q-input
          v-model="request.saveEmployee.data.salary"
          label="Salary"
          type="number"
          clearable
        />
      </q-card-section>
      <q-card-actions align="right">
        <q-btn
          flat
          label="Cancel"
          color="red"
          v-close-popup
          @click="cancelAddEmployee"
        />
        <q-btn
          flat
          label="Confirm"
          color="primary"
          v-close-popup
          @click="callSaveEmployee"
        />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
import { api } from "src/boot/axios";
import { ref, onMounted, reactive } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

let employees = ref([]);
let pagination = ref({});
let selected = ref([]);
let jobs = ref(null);
let departments = ref(null);
let departmentIds = ref(null);
let editDialog = ref(false);

let request = reactive({
  getEmployees: {
    method: "GET",
    url: "/employees",
    params: {},
  },
  getJobs: {
    method: "GET",
    url: "/jobsLabelValues",
  },
  getDepartments: {
    method: "GET",
    url: "/departmentsLabelValues",
  },

  saveEmployee: {
    method: "POST",
    url: "/employee/save",
    data: {},
  },
  deleteEmployee: {
    method: "POST",
    url: "/employee/delete",
    data: {},
  },
});

onMounted(() => {
  //callGetEmployees();
  callGetJobs();
  callGetDepartments();
});

const columns = [
  { label: "Employee ID", field: (row) => row.employeeId },
  { label: "First Name", field: (row) => row.firstName },
  { label: "Last Name", field: (row) => row.lastName },
  { label: "Email", field: (row) => row.email },
  { label: "Phone Number", field: (row) => row.phoneNumber },
  { label: "Salary", field: (row) => row.salary },
  { label: "Hire Date", field: (row) => row.hireDate },
  //{ label: "Job ID", field: (row) => row.jobId },
  //{ label: "Department ID", field: (row) => row.departmentId },
  { name: "jobTitle", label: "Job Title", field: (row) => row.jobId },
  {
    name: "departmentName",
    label: "Department Name",
    field: (row) => row.departmentId,
  },
];

/*columns = ref([
  { name: "employeeId", label: "Employee ID", field: (row) => row.employeeId },
]);
const columns = {
  data() {
    return {
      columns: [
        { label: "Employee ID", field: (row) => row.employeeId },
        { label: "First Name", field: (row) => row.firstName },
        { label: "Last Name", field: (row) => row.lastName },
        { label: "Email", field: (row) => row.email },
        { label: "Phone Number", field: (row) => row.phoneNumber },
        { label: "Salary", field: (row) => row.salary },
        { label: "Hire Date", field: (row) => row.hireDate },
        { label: "Job ID", field: (row) => row.jobId },
        { label: "Department ID", field: (row) => row.departmentId },
      ],
      employees: [],
    };
  },
};



*/

//formatDepartmentIds 會將陣列轉成字串或 null
function formatDepartmentIds(args) {
  let result = departmentIds.value ? departmentIds.value.toString() : null;
  request.getEmployees.params.departmentIds = result;
}

function alterPagination(args) {
  request.getEmployees.params.page = args.pagination.page - 1;
  request.getEmployees.params.size = args.pagination.rowsPerPage;
  callGetEmployees();
}

function getLabel(source, id) {
  return !id
    ? ""
    : this[source].find((each) => each.value.toString() === id.toString())
        .label;
}

function moveToAddEmployee() {
  router.push({
    name: "add-new-employee",
    params: {
      jobs: JSON.stringify(jobs.value),
      departments: JSON.stringify(departments.value),
    },
  });
}

function updateOpenDialog() {
  editDialog.value = true;
  request.saveEmployee.data = Object.assign({}, selected.value[0]);
}

function cancelAddEmployee() {
  request.saveEmployee.data = {};
  router.push("/demo").then();
}

async function callGetJobs() {
  let response = await api(request.getJobs);
  jobs.value = response.data;
}
async function callGetDepartments() {
  let response = await api(request.getDepartments);
  departments.value = response.data;
}

async function callGetEmployees() {
  formatDepartmentIds();
  let response = await api(request.getEmployees);
  employees.value = response.data.content;
  pagination.value.rowsNumber = response.data.totalElements;
  pagination.value.rowsPerPage = response.data.size;
  pagination.value.page = response.data.number + 1;
}

async function callDeleteEmployee() {
  request.deleteEmployee.data.employeeId = selected.value[0].employeeId;
  try {
    await api(request.deleteEmployee);
    alert(
      `Employee ID: ${request.deleteEmployee.data.employeeId}\nhas been deleted.`
    );
  } catch (error) {
    alert("Delete Failed");
    console.log(error);
  }
}

async function callSaveEmployee() {
  try {
    let response = await api(request.saveEmployee);
    alert(
      `Employee ID: ${response.data.employeeId}\nName: ${response.data.firstName} ${response.data.lastName}\nhas been saved successfully!`
    );
  } catch (error) {
    alert("Save Employee Faild");
    console.log(error);
  }
  router.push("/demo");
}
</script>

<style lang="scss" scoped></style>
