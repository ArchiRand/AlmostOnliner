package soso.production.controller;

import soso.production.model.Address;
import soso.production.model.dto.AddressDto;
import soso.production.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

@Controller("addressController")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @RequestMapping(value="/user/address", method= RequestMethod.GET)
    public String getAddressForm(Principal principal, Model model) {
        String email = principal.getName();
        Address userAddressObj = addressService.getAddressByUserEmail(email);

        String userCity = userAddressObj.getCity();
        String userStreet = userAddressObj.getStreet();
        String userBuildingNumber = userAddressObj.getBuildingNumber();
        String userPostalCode = userAddressObj.getPostalCode();

        AddressDto addressDto = new AddressDto(userStreet, userCity, userBuildingNumber, userPostalCode);
        model.addAttribute("addressForm", addressDto);
        return "address/editAddress";
    }

    @RequestMapping(value="/user/address", method=RequestMethod.POST)
    public String editAddress(Principal principal, @Valid @ModelAttribute("addressForm") AddressDto addressDto, BindingResult result) {
        if (result.hasErrors()) {
            return "address/editAddress";
        }
        String email = principal.getName();
        Address userAddressObj = addressService.getAddressByUserEmail(email);

        userAddressObj.setCity(addressDto.getCity());
        userAddressObj.setBuildingNumber(addressDto.getBuildingNumber());
        userAddressObj.setStreet(addressDto.getStreet());
        userAddressObj.setPostalCode(addressDto.getPostalCode());
        addressService.save(userAddressObj);
        return "redirect:/user";
    }
}
