package com.arnab.android_mysql_nodejs.network;

import android.util.Log;

import com.arnab.android_mysql_nodejs.employeesdb.pojo.Department;
import com.arnab.android_mysql_nodejs.pojo.Car;
import com.arnab.android_mysql_nodejs.pojo.Country;
import com.arnab.android_mysql_nodejs.pojo.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {

    public boolean getServerStatus(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.optInt("success") == 1;
        } catch (JSONException e) {
            Log.e("msg", "" + e);
            e.printStackTrace();
            return false;
        }
    }

    public void countryData(String response, ArrayList<Country> countries) {
        countries.clear();
        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                Country country = new Country();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                country.setCountryId(jsonObject.optInt("ID"));
                country.setCountryCode(jsonObject.optString("CountryCode"));
                country.setEnglishname(jsonObject.optString("EnglishName"));
                country.setFrenchname(jsonObject.optString("FrenchName"));
                countries.add(country);
            }
        } catch (JSONException e) {
            Log.e("msg", "" + e);
            e.printStackTrace();
        }
    }

    public void employeeData(String response, ArrayList<Employee> employees) {
        try {
            JSONObject responseJsonObject = new JSONObject(response);
            JSONArray jsonArray = responseJsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                Employee employee = new Employee();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                employee.setEmp_no(jsonObject.optInt("emp_no"));
                employee.setBirth_date(jsonObject.optString("birth_date"));
                employee.setName(jsonObject.optString("first_name") + " " + jsonObject.optString("last_name"));
                employee.setGender(jsonObject.optString("gender"));
                employee.setHire_date(jsonObject.optString("hire_date"));
                employee.setDept_no(jsonObject.optString("dept_no"));
                employee.setDept_name(jsonObject.optString("dept_name"));
                employee.setTitle(jsonObject.optString("title"));
                employee.setSalary(jsonObject.optInt("salary"));
                employee.setManagers(jsonObject.optString("managers"));
                employees.add(employee);
            }
        } catch (JSONException e) {
            Log.e("msg", "" + e);
            e.printStackTrace();
        }
    }

    public void parseEmployeeNameAndNo(String response, ArrayList<Employee> employees) {
        employees.clear();
        try {
            JSONObject responseJsonObject = new JSONObject(response);
            JSONArray jsonArray = responseJsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                Employee employee = new Employee();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                employee.setEmp_no(jsonObject.optInt("emp_no"));
                employee.setName(jsonObject.optString("name"));
                employees.add(employee);
            }
        } catch (JSONException e) {
            Log.e("msg", "" + e);
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> parseEmployeeSalary(String response) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            JSONObject responseJsonObject = new JSONObject(response);
            JSONArray jsonArray = responseJsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                Employee employee = new Employee();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                employee.setSalary(jsonObject.optInt("salary"));
                employee.setSalary_date(jsonObject.optString("date"));
                employees.add(employee);
            }
            return employees;
        } catch (JSONException e) {
            Log.e("msg", "" + e);
            e.printStackTrace();
            return null;
        }
    }

    public void parseCarData(ArrayList<Car> cars, String response) {
        try {
            JSONObject responseJsonObject = new JSONObject(response);
            JSONArray jsonArray = responseJsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                Car car = new Car();
                car.setProductCode(jsonArray.getJSONObject(i).optString("productCode"));
                car.setProductName(jsonArray.getJSONObject(i).optString("productName"));
                car.setProductLine(jsonArray.getJSONObject(i).optString("productLine"));
                car.setProductScale(jsonArray.getJSONObject(i).optString("productScale"));
                car.setProductVendor(jsonArray.getJSONObject(i).optString("productVendor"));
                car.setProductDescription(jsonArray.getJSONObject(i).optString("productDescription"));
                car.setQuantityInStock(jsonArray.getJSONObject(i).optInt("quantityInStock"));
                car.setBuyPrice(jsonArray.getJSONObject(i).optDouble("buyPrice"));
                car.setMSRP(jsonArray.getJSONObject(i).optDouble("MSRP"));
                car.setImage(jsonArray.getJSONObject(i).optString("image"));
                cars.add(car);
            }
        } catch (JSONException e) {
            Log.e("msg", "" + e);
            e.printStackTrace();
        }
    }

    public ArrayList<Department> getDepartments(String response) {
        ArrayList<Department> departmentsArrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                Department department = new Department();
                department.dept_no = jsonArray.getJSONObject(i).optString("dept_no");
                department.dept_name = jsonArray.getJSONObject(i).optString("dept_name");
                departmentsArrayList.add(department);
            }
            return departmentsArrayList;
        } catch (JSONException e) {
            Log.e("msg", "" + e);
            return null;
        }

    }

    public ArrayList<Employee> getEmployees(String response) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                Employee employee = new Employee();
                employee.setEmp_no(jsonArray.getJSONObject(i).optInt("emp_no"));
                employee.setName(jsonArray.getJSONObject(i).optString("full_name"));
                employee.setDept_name(jsonArray.getJSONObject(i).optString("dept_name"));
                employee.setDept_no(jsonArray.getJSONObject(i).optString("dept_no"));
                employees.add(employee);
            }
            return employees;
        } catch (JSONException e) {
            Log.e("msg", "" + e);
            return null;
        }
    }
}
