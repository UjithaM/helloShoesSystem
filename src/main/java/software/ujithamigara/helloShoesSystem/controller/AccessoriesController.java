package software.ujithamigara.helloShoesSystem.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import software.ujithamigara.helloShoesSystem.dto.AccessoriesDTO;
import software.ujithamigara.helloShoesSystem.service.AccessoriesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accessories")
@RequiredArgsConstructor
public class AccessoriesController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final AccessoriesService accessoriesService;

    @GetMapping("/health")
    public String healthTest(){
        return "Accessories Health Test";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveAccessories(@Validated @RequestBody AccessoriesDTO accessoriesDTO, BindingResult bindingResult){
        logger.info("Saving accessories details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            accessoriesService.saveAccessories(accessoriesDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Accessories Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Accessories saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AccessoriesDTO> getAllAccessories() {
        logger.info("Fetching all accessories");
        return accessoriesService.getAllAccessories();
    }

    @GetMapping("/{id}")
    public AccessoriesDTO getAccessoriesById(@PathVariable String id) {
        logger.info("Fetching accessories with ID: {}", id);
        return accessoriesService.getSelectedAccessories(id);
    }

    @PutMapping("/{id}")
    public void updateAccessories(@PathVariable String id, @RequestBody AccessoriesDTO accessoriesDTO) {
        logger.info("Updating accessories with ID: {}", id);
        accessoriesService.updateAccessories(id, accessoriesDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAccessories(@PathVariable String id) {
        logger.info("Deleting accessories with ID: {}", id);
        accessoriesService.deleteAccessories(id);
    }
}
