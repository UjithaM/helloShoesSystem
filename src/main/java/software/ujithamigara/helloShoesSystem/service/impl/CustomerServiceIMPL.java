package software.ujithamigara.helloShoesSystem.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.ujithamigara.helloShoesSystem.dao.CustomerRepo;
import software.ujithamigara.helloShoesSystem.dto.CustomerDTO;
import software.ujithamigara.helloShoesSystem.entity.CustomerEntity;
import software.ujithamigara.helloShoesSystem.service.CustomerService;
import software.ujithamigara.helloShoesSystem.util.Mapping;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {
    private final CustomerRepo repo;
    private final Mapping mapping;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(UUID.randomUUID().toString());
        return mapping.toCustomerDTO(repo.save(mapping.toCustomer(customerDTO)));
    }

    @Override
    public void deleteCustomer(String customerId) {
        repo.delete(repo.getReferenceById(customerId));
    }

    @Override
    public CustomerDTO getSelectedCustomer(String customerId) {
        return mapping.toCustomerDTO(repo.getReferenceById(customerId));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return mapping.toCustomerDTOList(repo.findAll());
    }

    @Override
    public void updateCustomer(String customerId, CustomerDTO customerDTO) {
        CustomerEntity customer = repo.getReferenceById(customerId);
        customer.setName(customerDTO.getName());
        customer.setGender(customerDTO.getGender());
        customer.setJionDate(customerDTO.getJionDate());
        customer.setLevel(customerDTO.getLevel());
        customer.setTotalPoints(customerDTO.getTotalPoints());
        customer.setDob(customerDTO.getDob());
        customer.setAddressLine01(customerDTO.getAddressLine01());
        customer.setAddressLine02(customerDTO.getAddressLine02());
        customer.setAddressLine03(customerDTO.getAddressLine03());
        customer.setAddressLine04(customerDTO.getAddressLine04());
        customer.setAddressLine05(customerDTO.getAddressLine05());
        customer.setContactNumber(customerDTO.getContactNumber());
        customer.setEmail(customerDTO.getEmail());
        customer.setRecentPurchaseDate(customerDTO.getRecentPurchaseDate());
        repo.save(customer);
    }
}
