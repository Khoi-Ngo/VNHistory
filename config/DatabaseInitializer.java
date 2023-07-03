package com.swp.vnhistory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.swp.vnhistory.model.Dynasty;
import com.swp.vnhistory.model.DynastyEnum;
import com.swp.vnhistory.model.Location;
import com.swp.vnhistory.model.Province;
import com.swp.vnhistory.repository.IDynastyRepo;
import com.swp.vnhistory.repository.ILocationRepo;

@Component
public class DatabaseInitializer implements CommandLineRunner {

	@Autowired
	private ILocationRepo locationRepository;

	@Autowired
	private IDynastyRepo dynastyRepo;

	@Override
	public void run(String... args) throws Exception {

		try {

			int count = 1;
			for (Province i : Province.values()) {
				Location location = new Location(count, i.getName(), i.getDescription());

				locationRepository.save(location);
				count++;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			long count = 1;
			for (DynastyEnum i : DynastyEnum.values()) {
				Dynasty dynasty = new Dynasty(count, i.getDynastyName(), i.getDynastyDescription(), i.getStartYear(),
						i.getEndYear());
				dynastyRepo.save(dynasty);
				count++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
