package software.ujithamigara.helloShoesSystem.service;

import software.ujithamigara.helloShoesSystem.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO saveOrder(OrderDTO orderDTO);
    OrderDTO getSelectedOrder(String orderCode);
    List<OrderDTO> getAllOrder();
    void updateOrder(String orderCode,OrderDTO orderDTO);
}
