import re
import time
import unittest
import urllib
import requests
from seleniumwire import webdriver


from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select

from create_course.config import config

class TestCreateEvent(unittest.TestCase):
    LOGIN_ENDPOINT = config["moodleUrl"] + "/login/index.php"
    MANAGE_ENDPOINT = config["moodleUrl"] + "/course/management.php"
    ENDPOINT = config["moodleUrl"] + "/course/edit.php?category=1&returnto=catmanage"

    def setUp(self):
        options = webdriver.ChromeOptions()
        options.headless = True
        options.add_experimental_option("prefs", {'profile.managed_default_content_settings.javascript': 2})
        self.webdriver = webdriver.Chrome(ChromeDriverManager().install(), options=options)
        self.webdriver.set_window_size(1920, 1080)
        self.webdriver.maximize_window()
        self.login_moodle()

    def tearDown(self):
        return self.webdriver.quit()

    def test_cc001(self):
        name = config["courseName"]["cc001"]
        self.create_event_with_name(name)
        self.assertFalse(self.course_is_accepted(name["full"]))

    def test_cc002(self):
        name = config["courseName"]["cc002"]
        self.create_event_with_name(name)
        self.assertTrue(self.course_is_accepted(name["full"]))

    def test_cc003(self):
        name = config["courseName"]["cc003"]
        self.create_event_with_name(name)
        self.assertTrue(self.course_is_accepted(name["full"]))

    def test_cc004(self):
        name = config["courseName"]["cc004"]
        self.create_event_with_name(name)
        self.assertTrue(self.course_is_accepted(name["full"]))

    def test_cc005(self):
        name = config["courseName"]["cc005"]
        self.create_event_with_name(name)
        self.assertTrue(self.course_is_accepted(name["full"]))

    def test_cc006(self):
        name = config["courseName"]["cc006"]
        self.create_event_with_name(name)
        self.assertFalse(self.course_is_accepted(name["full"]))

    def test_cc007(self):
        name = config["courseName"]["cc007"]
        self.create_event_with_name(name)
        self.assertFalse(self.course_is_accepted(name["full"]))

    def test_cc008(self):
        name = config["courseName"]["cc008"]
        self.create_event_with_name(name)
        self.assertTrue(self.course_is_accepted(name["full"]))

    def test_cc009(self):
        name = config["courseName"]["cc009"]
        self.create_event_with_name(name)
        self.assertTrue(self.course_is_accepted(name["full"]))

    def test_cc010(self):
        name = config["courseName"]["cc010"]
        self.create_event_with_name(name)
        self.assertTrue(self.course_is_accepted(name["full"]))

    def test_cc011(self):
        name = config["courseName"]["cc011"]
        self.create_event_with_name(name)
        self.assertTrue(self.course_is_accepted(name["full"]))

    def test_cc012(self):
        name = config["courseName"]["cc012"]
        self.create_event_with_name(name)
        self.assertFalse(self.course_is_accepted(name["full"]))

    def test_cc013(self):
        name = config["courseName"]["cc013"]
        self.create_event_with_name(name)
        self.assertTrue(self.course_is_accepted(name["full"]))

    def course_is_accepted(self, full_name):
        self.webdriver.get(self.MANAGE_ENDPOINT)
        try:
            self.wait_driver(
                EC.presence_of_element_located((
                    By.XPATH,
                    f"//*[contains(@class, 'coursename') and text() = '{full_name}']"
                ))
            )
            return True
        except:
            return False


    def create_event_with_name(self, name):
        self.webdriver.get(self.ENDPOINT)
        fullNameField = self.wait_driver(
            EC.presence_of_element_located((By.ID, "id_fullname"))
        )
        # remove length limit of HTML5
        self.webdriver.execute_script("arguments[0].setAttribute('maxlength', 999999)", fullNameField)
        fullNameField.send_keys(name["full"])
        shortNameField = self.webdriver.find_element(By.ID, "id_shortname")
        # remove length limit of HTML5
        self.webdriver.execute_script("arguments[0].setAttribute('maxlength', 999999)", shortNameField)
        shortNameField.send_keys(name["short"])
        saveBtn = self.webdriver.find_element(By.ID, "id_saveandreturn")
        saveBtn.click()


    def login_moodle(self):
        self.webdriver.get(self.LOGIN_ENDPOINT)
        userNameField = self.webdriver.find_element(By.ID, "username")
        passwordField = self.webdriver.find_element(By.ID, "password")
        logInBtn = self.webdriver.find_element(By.ID, "loginbtn")
        userNameField.send_keys(config["username"])
        passwordField.send_keys(config["password"])
        logInBtn.click()
        cookies = self.webdriver.get_cookies()

        for cookie in cookies:
            if cookie['name'] == 'MoodleSession':
                self.assertTrue(len(cookie['value']) == 26, "Failed to log in")
                return
        self.fail("Failed to log in")
    

    def wait_driver(self, condition, timeout = 10):
        wait = WebDriverWait(self.webdriver, timeout)
        return wait.until(condition)


if __name__ == '__main__':
    unittest.main()