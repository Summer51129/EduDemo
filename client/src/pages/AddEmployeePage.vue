<template>
  <div class="q-pa-xl">
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
            clearables
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
  </div>
</template>

<script setup>
import { api } from "src/boot/axios";
import { ref, onMounted, reactive } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const jobs = ref(null);
const departments = ref(null);

let editDialog = ref(true);

let request = reactive({
  saveEmployee: {
    method: "POST",
    url: "/employee/save",
    data: {},
  },
  getJobs: {
    method: "GET",
    url: "/jobsLabelValues",
  },
  getDepartments: {
    method: "GET",
    url: "/departmentsLabelValues",
  },
});

onMounted(() => {
  //callGetEmployees();
  callGetJobs();
  callGetDepartments();
});

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
  router.push("/demo").then;
}

function cancelAddEmployee() {
  request.saveEmployee.data = {};
  router.push("/demo");
}

async function callGetJobs() {
  let response = await api(request.getJobs);
  jobs.value = response.data;
}
async function callGetDepartments() {
  let response = await api(request.getDepartments);
  departments.value = response.data;
}
</script>
