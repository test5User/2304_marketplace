package by.itclass.model.services;

import java.util.Map;

public class ServiceFactory {
    private static Map<ServiceType, Service> services;

    public static void init() {
        services = Map.ofEntries(
          Map.entry(ServiceType.CART_SERVICE, new CartService()),
          Map.entry(ServiceType.LAPTOP_SERVICE, new LaptopService()),
          Map.entry(ServiceType.ORDER_SERVICE, new OrderService()),
          Map.entry(ServiceType.TV_SERVICE, new TvService()),
          Map.entry(ServiceType.USER_SERVICE, new UserService())
        );
    }

    public static Service getInstance(ServiceType serviceType) {
        return services.get(serviceType);
    }
}
