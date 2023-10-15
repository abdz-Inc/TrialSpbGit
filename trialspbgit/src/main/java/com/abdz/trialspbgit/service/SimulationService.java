package com.abdz.trialspbgit.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdz.trialspbgit.dao.MeterDAO;
import com.abdz.trialspbgit.dao.PowerDAO;
import com.abdz.trialspbgit.enitity.Meter;
import com.abdz.trialspbgit.enitity.Power;
import com.abdz.trialspbgit.form.PowerForm;
import com.abdz.trialspbgit.form.SmartMeterForm;

@Service
public class SimulationService {

    private MeterDAO meterDAO;
    private PowerDAO powerDAO;


    @Autowired
    public SimulationService(MeterDAO meterDAO, PowerDAO powerDAO)
    {
        this.meterDAO = meterDAO;
        this.powerDAO = powerDAO;
    }
    
    public List<Meter> getAllMeters()
    {
        return meterDAO.findAll();
    }

    public int getMeterPowerMode(int mid,String mode)
    {
        List<Power> powers = powerDAO.findByIdAndMode(mid, mode);
        int powerMode = 0;
        for(Power p : powers)
        {
            powerMode+=p.getQuantity();
        }
        return powerMode;
    }

    public int getAvailablePowerInMeter(int mid)
    {
        
        int generated = getMeterPowerMode(mid, "generated");
        int sold = getMeterPowerMode(mid, "sold");
        int consumed = getMeterPowerMode(mid, "consumed");
        int purchased = getMeterPowerMode(mid, "purchased");

        return (generated + purchased) - (sold + consumed);

    }

    public HashMap<Integer, Meter> getAllAvailablePowerInMeter(List<Meter> meters)
    {
        HashMap<Integer, Meter> mp = new HashMap<Integer, Meter>();
        for(Meter m : meters)
        {
            mp.put(getAvailablePowerInMeter(m.getMid()), m);
        }
        return mp;
    } 

    public Meter createMeterFromForm(SmartMeterForm smartmeterform)
    {
        Meter meter = new Meter(smartmeterform.getCapacity(), smartmeterform.getName(), smartmeterform.getAddress(), smartmeterform.getPhnno());
        meterDAO.save(meter);
        return meter;
    }

    public Power createPowerFromForm(PowerForm powerform)
    {
        Power power = new Power(powerform.getMid(), powerform.getMode(), powerform.getQuantity());
        powerDAO.save(power);
        return power;
    }

}
