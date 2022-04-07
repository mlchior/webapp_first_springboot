package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all employees
     * @return An iterable of all employees
     */

    public Iterable<Employee> getEmployees() {
        /**on recupere l'url de l'API grace a la propriete creer customProperties */
        String baseApiUrl = props.getApiUrl();
        /**on complète l’URL de l’API par le path de l'endpoint à joindre. */
        String getEmployeesUrl = baseApiUrl + "/employees";
        /**instancie object RestTemplate. */
        RestTemplate restTemplate = new RestTemplate();
        /**appel de la methode Exchange en transmettant l'url, HttpMethod et le type de retour*/
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {}
        );

        log.debug("Get Employees call " + response.getStatusCode().toString());
    /**on retourne la liste des employés avec la methode getBody de l'objet Response*/
        return response.getBody();
    }
    public Employee createEmployee(Employee e) {
        /**on recupere l'url de l'API grace a la propriete creer customProperties */
        String baseApiUrl = props.getApiUrl();
        /**on complète l’URL de l’API par le path de l'endpoint à joindre. */
        String createEmployeeUrl = baseApiUrl + "/employees";
        /**instancie object RestTemplate. */
        RestTemplate restTemplate = new RestTemplate();
        //on crée un objet HttpEntity qui contient le body de la requête
        HttpEntity<Employee> request = new HttpEntity<Employee>(e);
        /**appel de la methode Exchange en transmettant l'url, HttpMethod et le type de retour*/
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class);


        log.debug("Create Employee call " + response.getStatusCode().toString());
    /**on retourne l'employé avec la methode getBody de l'objet Response*/
        return response.getBody();
    }

    public Employee getEmployee() {
        /**on recupere l'url de l'API grace a la propriete creer customProperties */
        String baseApiUrl = props.getApiUrl();
        /**on complète l’URL de l’API par le path de l'endpoint à joindre. */
        String getEmployeeUrl = baseApiUrl + "/employees/1";
        /**instancie object RestTemplate. */
        RestTemplate restTemplate = new RestTemplate();
        /**appel de la methode Exchange en transmettant l'url, HttpMethod et le type de retour*/
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class);

        log.debug("Get Employee call " + response.getStatusCode().toString());
    /**on retourne l'employé avec la methode getBody de l'objet Response*/
        return response.getBody();
    }

    public void deleteEmployee(int id) {
        /**on recupere l'url de l'API grace a la propriete creer customProperties */
        String baseApiUrl = props.getApiUrl();
        /**on complète l’URL de l’API par le path de l'endpoint à joindre. */
        String deleteEmployeeUrl = baseApiUrl + "/employees/" + id;
        /**instancie object RestTemplate. */
        RestTemplate restTemplate = new RestTemplate();
        /**appel de la methode Exchange en transmettant l'url, HttpMethod et le type de retour*/
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Employee call " + response.getStatusCode().toString());
    }

    public Employee updateEmployee(Employee employee) {
        /**on recupere l'url de l'API grace a la propriete creer customProperties */
        String baseApiUrl = props.getApiUrl();
        /**on complète l’URL de l’API par le path de l'endpoint à joindre. */
        String updateEmployeeUrl = baseApiUrl + "/employees/" + employee.getId();
        /**instancie object RestTemplate. */
        RestTemplate restTemplate = new RestTemplate();
        //on crée un objet HttpEntity qui contient le body de la requête
        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
        /**appel de la methode Exchange en transmettant l'url, HttpMethod et le type de retour*/
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeeUrl,
                HttpMethod.PUT,
                request,
                Employee.class);

        log.debug("Update Employee call " + response.getStatusCode().toString());
    /**on retourne l'employé avec la methode getBody de l'objet Response*/
        return response.getBody();
    }
}