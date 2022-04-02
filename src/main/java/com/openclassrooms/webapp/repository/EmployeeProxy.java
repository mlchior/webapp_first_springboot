package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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

}