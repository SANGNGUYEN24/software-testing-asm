import re
import time
import unittest
import requests
from seleniumwire import webdriver


from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select

from create_event.config import config

class TestCreateEvent(unittest.TestCase):
    LOGIN_ENDPOINT = config["moodleUrl"] + "/login/index.php"
    ENDPOINT = config["moodleUrl"] + "/calendar/view.php"

    def setUp(self):
        options = webdriver.ChromeOptions()
        options.headless = True
        self.webdriver = webdriver.Chrome(ChromeDriverManager().install(), options=options)
        self.webdriver.set_window_size(1920, 1080)
        self.webdriver.maximize_window()
        self.login_moodle()
        self.get_template_request()
        self.assertIsNotNone(self.request)

    def tearDown(self):
        return self.webdriver.quit()

    def test_ce001(self):
        validError = self.create_event_with_date(config["date"]["ce001"])
        self.assertFalse(validError)

    def test_ce002(self):
        validError = self.create_event_with_date(config["date"]["ce002"])
        self.assertFalse(validError)

    def test_ce003(self):
        validError = self.create_event_with_date(config["date"]["ce003"])
        self.assertFalse(validError)

    def test_ce004(self):
        validError = self.create_event_with_date(config["date"]["ce004"])
        self.assertFalse(validError)

    def test_ce005(self):
        validError = self.create_event_with_date(config["date"]["ce005"])
        self.assertFalse(validError)

    def test_ce006(self):
        validError = self.create_event_with_date(config["date"]["ce006"])
        self.assertFalse(validError)

    def test_ce007(self):
        validError = self.create_event_with_date(config["date"]["ce007"])
        self.assertFalse(validError)

    def test_ce008(self):
        validError = self.create_event_with_date(config["date"]["ce008"])
        self.assertFalse(validError)

    def test_ce009(self):
        validError = self.create_event_with_date(config["date"]["ce009"])
        self.assertFalse(validError)

    def test_ce010(self):
        validError = self.create_event_with_date(config["date"]["ce010"])
        self.assertFalse(validError)

    def test_ce011(self):
        validError = self.create_event_with_date(config["date"]["ce011"])
        self.assertFalse(validError)

    def test_ce012(self):
        validError = self.create_event_with_date(config["date"]["ce012"])
        self.assertTrue(validError)

    def test_ce013(self):
        validError = self.create_event_with_date(config["date"]["ce013"])
        self.assertFalse(validError)

    def test_ce014(self):
        validError = self.create_event_with_date(config["date"]["ce014"])
        self.assertFalse(validError)
    
    def test_ce015(self):
        validError = self.create_event_with_date(config["date"]["ce015"])
        self.assertFalse(validError)

    def test_ce016(self):
        validError = self.create_event_with_date(config["date"]["ce016"])
        self.assertFalse(validError)

    def test_ce017(self):
        validError = self.create_event_with_date(config["date"]["ce017"])
        self.assertTrue(validError)

    def test_ce018(self):
        validError = self.create_event_with_date(config["date"]["ce018"])
        self.assertTrue(validError)

    def test_ce019(self):
        validError = self.create_event_with_date(config["date"]["ce019"])
        self.assertTrue(validError)

    def test_ce020(self):
        validError = self.create_event_with_date(config["date"]["ce020"])
        self.assertTrue(validError)

    def test_ce021(self):
        validError = self.create_event_with_date(config["date"]["ce021"])
        self.assertFalse(validError)

    def test_ce022(self):
        validError = self.create_event_with_date(config["date"]["ce022"])
        self.assertFalse(validError)

    def test_ce023(self):
        validError = self.create_event_with_date(config["date"]["ce023"])
        self.assertTrue(validError)

    def test_ce024(self):
        validError = self.create_event_with_date(config["date"]["ce024"])
        self.assertTrue(validError)

    def create_event_with_date(self, date):
        day, month, year = date.split("/")
        form_data = re.sub(r"timestart%5Bday%5D=\d+", "timestart%5Bday%5D=" + day, self.request["body"])
        form_data = re.sub(r"timestart%5Bmonth%5D=\d+", "timestart%5Bmonth%5D=" + month, form_data)
        form_data = re.sub(r"timestart%5Byear%5D=\d+", "timestart%5Byear%5D=" + year, form_data)
        res = requests.post(
            url=self.request["url"],
            data=form_data,
            cookies=self.request["cookies"]
        )
        validError = res.json()[0]["data"]["validationerror"]
        return validError

    def get_template_request(self):
        self.webdriver.get(self.ENDPOINT)
        newEventBtn = self.wait_driver(
            EC.element_to_be_clickable((By.XPATH, "//*[@data-action='new-event-button']"))
        )
        newEventBtn.click()
        eventTitle = self.wait_driver(
            EC.presence_of_element_located((By.ID, "id_name"))
        )
        eventTitle.send_keys("Test")
        dayField = Select(self.webdriver.find_element(By.NAME, "timestart[day]"))
        dayField.select_by_value("19")
        monthField = Select(self.webdriver.find_element(By.NAME, "timestart[month]"))
        monthField.select_by_value("7")
        yearField = Select(self.webdriver.find_element(By.NAME, "timestart[year]"))
        yearField.select_by_value("2001")
        saveBtn = self.webdriver.find_element(By.XPATH, "//*[@data-action='save']")
        saveBtn.click()
        time.sleep(10)

        # capture raw request
        idx = len(self.webdriver.requests) - 1
        
        while idx >= 0:
            req = self.webdriver.requests[idx]
            if "core_calendar_submit_create_update_form" in req.url:
                self.request = {}
                self.request["body"] = req.body.decode()
                self.request["url"] = req.url
                for cookie in self.webdriver.get_cookies():
                    if cookie["name"] == "MoodleSession":
                        self.request["cookies"] = {"MoodleSession": cookie["value"]}
                return
            idx -= 1

        self.request = None


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