package com.code.controller;

import com.code.model.Admin;
import com.code.model.JobSeeker;
import com.code.model.Recruiter;
import com.code.service.AdminService;
import com.code.service.JobSeekerService;
import com.code.service.JobService;
import com.code.service.RecruiterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private JobService jobService;

	@Autowired
	private JobSeekerService jobSeekerService;

	@Autowired
	private RecruiterService recruiterService;

	// @GetMapping("/dashboard")
	// public ModelAndView admin() {
	// ModelAndView modelAndView = new ModelAndView("admin/dashboard");
	// modelAndView.addObject("jobSeeker", jobSeeker);
	// return modelAndView;
	// }

	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		// Check if admin is logged in
		if (session.getAttribute("currentAdmin") == null) {
			return "redirect:/login";
		}

		// Fetch counts for dashboard
		model.addAttribute("totalJobSeekers", jobSeekerService.countJobSeekers());
		model.addAttribute("totalJobs", jobService.countJobs());
		model.addAttribute("totalRecruiters", recruiterService.countRecruiters());

		return "admin/dashboard";
	}

	@GetMapping("/profile")
	public String showProfile(HttpSession session, Model model) {
		Admin admin = (Admin) session.getAttribute("currentAdmin");
		if (admin == null) {
			return "redirect:/login"; // Redirect to login if admin is not logged in
		}
		model.addAttribute("admin", admin);
		return "admin/profile";
	}

	// @PostMapping("/profile/update")
	// public String updateProfile(@Valid @ModelAttribute("admin") Admin admin,
	// BindingResult bindingResult, HttpSession session, Model model) {
	// if (bindingResult.hasErrors()) {
	// return "admin/profile";
	// }

	// Admin currentAdmin = (Admin) session.getAttribute("currentAdmin");
	// if (currentAdmin == null) {
	// return "redirect:/login"; // Redirect to login if admin is not logged in
	// }

	// // Update the admin details
	// admin.setAdminId(currentAdmin.getAdminId());
	// adminService.saveAdmin(admin);

	// // Update the session
	// session.setAttribute("currentAdmin", admin);

	// model.addAttribute("success", "Profile updated successfully.");
	// return "admin/profile";
	// }

	// @GetMapping("/login")
	// public String showLoginForm() {
	// return "admin/login";
	// }

	// @PostMapping("/login")
	// public ModelAndView loginAdmin(@RequestParam String email, @RequestParam
	// String password, HttpSession session) {
	// ModelAndView modelAndView = new ModelAndView();
	// Admin admin = adminService.findByEmailAndPassword(email, password);

	// if (admin == null) {
	// modelAndView.setViewName("admin/login");
	// modelAndView.addObject("error", "Invalid email or password");
	// return modelAndView;
	// }

	// // Set admin in session
	// session.setAttribute("currentAdmin", admin);
	// modelAndView.setViewName("redirect:/admin/dashboard");
	// return modelAndView;
	// }

	// @GetMapping("/logout")
	// public String logout(HttpSession session) {
	// session.invalidate(); // Invalidate the session
	// return "redirect:/admin/login"; // Redirect to login page
	// }

	// @GetMapping("/jobseeker-list")
	// public ModelAndView jobseekerList() {

	// List<JobSeeker> jobSeekerList = jobSeekerService.findAllJobSeekers();
	// ModelAndView modelAndView = new ModelAndView("admin/jobseeker");
	// modelAndView.addObject("jobSeekers", jobSeekerList);
	// return modelAndView;
	// }

	@GetMapping("/jobseeker-list")
	public String manageJobSeekers(Model model, HttpSession session) {
		model.addAttribute("jobSeekers", jobSeekerService.findAllJobSeekers());
		String msg = (String) session.getAttribute("msg");
		if (msg != null) {
			model.addAttribute("msg", msg);
			session.removeAttribute("msg");
		}
		return "admin/jobseeker-list";
	}

	@GetMapping("/job-list")
	public String manageJobs(Model model, HttpSession session) {
		model.addAttribute("jobs", jobService.findAllJobs());
		String msg = (String) session.getAttribute("msg");
		if (msg != null) {
			model.addAttribute("msg", msg);
			session.removeAttribute("msg");
		}
		return "admin/job-list";
	}

	@GetMapping("/recruiter-list")
	public String manageRecruiters(Model model, HttpSession session) {
		model.addAttribute("recruiters", recruiterService.findAllRecruiters());

		// Retrieve and clear the session message
		String msg = (String) session.getAttribute("msg");
		if (msg != null) {
			model.addAttribute("msg", msg);
			session.removeAttribute("msg");
		}
		return "admin/recruiter-list";
	}

	@GetMapping("/pending-approvals")
	public String pendingApprovals(Model model) {
		model.addAttribute("pendingApprovals", recruiterService.findPendingApprovals());
		return "admin/pending-approvals";
	}

	@GetMapping("/delete/{jobSeekerId}")
	public String deleteJobseeker(@PathVariable int jobSeekerId, HttpSession session) {
		boolean result = jobSeekerService.deleteJobSeeker(jobSeekerId);

		if (result) {
			session.setAttribute("msg", "Student with rollno " + jobSeekerId + " deleted successfully");

		} else {
			session.setAttribute("msg", "Student with rollno " + jobSeekerId + " not found");
		}
		return "redirect:/admin/jobseeker-list";
	}

	// @GetMapping("/recruiter/edit/{id}")
	// public String editRecruiter(@PathVariable int id, Model model) {
	// 	Recruiter recruiter = recruiterService.findByRecruiterId(id);
	// 	if (recruiter == null) {
	// 		return "redirect:/admin/recruiter-list"; // Redirect if recruiter not found
	// 	}
	// 	model.addAttribute("recruiter", recruiter);
	// 	return "admin/edit-recruiter";
	// }

	@PostMapping("/recruiter/update")
	public String updateRecruiter(@Valid @ModelAttribute("recruiter") Recruiter recruiter, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/edit-recruiter";
		}
		recruiterService.saveRecruiter(recruiter);
		model.addAttribute("success", "Recruiter updated successfully.");
		return "redirect:/admin/recruiter-list";
	}

	@GetMapping("/delete-recruiter/{recruiterId}")
	public String deleteRecruiter(@PathVariable int recruiterId, HttpSession session) {
		boolean result = recruiterService.deleteRecruiter(recruiterId);

		if (result) {
			session.setAttribute("msg", "Recruiter with ID " + recruiterId + " deleted successfully.");
		} else {
			session.setAttribute("msg", "Recruiter with ID " + recruiterId + " not found.");
		}

		return "redirect:/admin/recruiter-list";
	}

	@GetMapping("/jobseeker/edit/{id}")
	public String editJobSeeker(@PathVariable int id, Model model) {
		JobSeeker jobSeeker = jobSeekerService.findById(id);
		if (jobSeeker == null) {
			return "redirect:/admin/jobseeker-list"; // Redirect if job seeker not found
		}
		model.addAttribute("jobSeeker", jobSeeker);
		return "admin/edit-jobseeker";
	}

	@PostMapping("/jobseeker/update")
	public String updateJobSeeker(@Valid @ModelAttribute("jobSeeker") JobSeeker jobSeeker, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/edit-jobseeker";
		}
		jobSeekerService.saveOrUpdate(jobSeeker);
		model.addAttribute("success", "Job Seeker updated successfully.");
		return "redirect:/admin/jobseeker-list";
	}
}
