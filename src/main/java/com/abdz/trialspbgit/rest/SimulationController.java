package com.abdz.trialspbgit.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdz.trialspbgit.enitity.Meter;
import com.abdz.trialspbgit.enitity.Power;
import com.abdz.trialspbgit.form.PowerForm;
import com.abdz.trialspbgit.form.SmartMeterForm;
import com.abdz.trialspbgit.service.PowerService;
import com.abdz.trialspbgit.service.SimulationService;

@Controller
@RequestMapping("/simulate")
public class SimulationController {

    private SimulationService simulationService;

    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/")
    public String simulate(Model model)
    {
        List<Meter> smartmeters = simulationService.getAllMeters();

        HashMap<Meter, Integer> smartmeterandpower = simulationService.getAllAvailablePowerInMeter(smartmeters);

        model.addAttribute("powerform", new PowerForm());
        model.addAttribute("smartmeters", smartmeterandpower);
        return "simulate";
    }

    @GetMapping("/smartmeters")
    public String simulateSmartMeter(Model model)
    {
        model.addAttribute("smartmeterform", new SmartMeterForm());
        return "simulatesmartmeter";
    }

    @PostMapping("/addsmartmeter")
    public String addSmartMeter(@ModelAttribute("smartmeterform") SmartMeterForm smartmeterform, Model model)
    {
        Meter meter = simulationService.createMeterFromForm(smartmeterform);
        model.addAttribute("meter", meter);
        return "simulatesmartmetersuccess";
    }

    @GetMapping("/power")
    public String simulatePower(@RequestParam("mid") int mid, Model model)
    {
        int availablepower = simulationService.getAvailablePowerInMeter(mid);
        model.addAttribute("mid", mid);
        model.addAttribute("consumptionlimit", availablepower);
        model.addAttribute("powerform", new PowerForm());
        return "simulatepower";
    }

    @PostMapping("/addpower")
    public String addPower(@ModelAttribute("powerform") PowerForm powerform, Model model)
    {
        System.out.println(powerform);
        Power power = simulationService.createPowerFromForm(powerform);
        model.addAttribute("power", power);
        return "powersuccess";
    }

}
