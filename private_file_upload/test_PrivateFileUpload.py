import os
import unittest
import requests
import time
from seleniumwire import webdriver

from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select

from private_file_upload.config import config

class TestPrivateFileUpload(unittest.TestCase):
    CWD = os.path.abspath(os.getcwd())
    LOGIN_ENDPOINT = config["moodleUrl"] + "/login/index.php"
    ENDPOINT = config["moodleUrl"] + "/user/files.php"

    def setUp(self):
        options = webdriver.ChromeOptions()
        options.headless = True
        self.webdriver = webdriver.Chrome(ChromeDriverManager().install(), options=options)
        self.webdriver.set_window_size(1920, 1080)
        self.webdriver.maximize_window()
        self.login_moodle()

    def tearDown(self):
        return self.webdriver.quit()

    def test_pf001(self):
        filename = config["fileName"]["pf001"]
        self.upload_file(filename)
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf002(self):
        filename = config["fileName"]["pf002"]
        self.upload_file(filename)
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf003(self):
        filename = config["fileName"]["pf003"]
        self.upload_file(filename)
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf004(self):
        filename = config["fileName"]["pf004"]
        self.upload_file(filename)
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf005(self):
        filename = config["fileName"]["pf005"]
        self.upload_file(filename)
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf006(self):
        filename = config["fileName"]["pf006"]
        self.upload_file(filename)
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf007(self):
        filename = config["fileName"]["pf007"]
        self.upload_file(filename)
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf008(self):
        filename = config["fileName"]["pf008"]
        self.upload_file(filename)
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf009(self):
        filename = config["fileName"]["pf009"]
        self.upload_file(filename)
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf010(self):
        filename = config["fileName"]["pf010"]
        self.upload_file(filename)
        self.assertFalse(self.file_is_accepted(filename))

    def test_pf011(self):
        filename = config["fileName"]["pf011"]
        self.upload_file(filename)
        self.change_file_metadata(filename, config["new_metadata"]["pf011"])
        self.save_changes()
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf012(self):
        filename = config["fileName"]["pf012"]
        self.upload_file(filename)
        self.change_file_metadata(filename, config["new_metadata"]["pf012"])
        self.save_changes()
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf013(self):
        filename = config["fileName"]["pf013"]
        self.upload_file(filename)
        self.change_file_metadata(filename, config["new_metadata"]["pf013"])
        self.save_changes()
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf014(self):
        filename = config["fileName"]["pf014"]
        self.upload_file(filename)
        self.change_file_metadata(filename, config["new_metadata"]["pf014"])
        self.save_changes()
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf015(self):
        filename = config["fileName"]["pf015"]
        self.upload_file(filename)
        self.change_file_metadata(filename, config["new_metadata"]["pf015"])
        self.save_changes()
        self.assertTrue(self.file_is_accepted(filename))

    def test_pf016(self):
        filename = config["fileName"]["pf016"]
        self.upload_file(filename)
        self.leave(True)
        self.assertFalse(self.file_is_accepted(filename))

    def test_pf017(self):
        filename = config["fileName"]["pf017"]
        self.upload_file(filename)
        self.leave(False)
        self.save_changes()
        self.assertTrue(self.file_is_accepted(filename))

    def leave(self, confirm_option):
        self.webdriver.refresh()
        self.wait_driver(
            EC.alert_is_present()
        )
        if confirm_option:
            self.webdriver.switch_to.alert.accept()
        else:
            self.webdriver.switch_to.alert.dismiss()

    def save_changes(self):
        saveBtn = self.wait_driver(
            EC.element_to_be_clickable((By.NAME, "submitbutton"))
        )
        self.webdriver.execute_script("arguments[0].click();", saveBtn)

    def change_file_metadata(self, filename, new_metadata):
        fileItem = self.wait_driver(
            EC.element_to_be_clickable((
                By.XPATH,
                f"//*[contains(@class,'fp-filename') and (text()='{filename}')]"
            ))
        )
        fileItem.click()
        
        if new_metadata["filename"] is not None:
            fileNameField = self.wait_driver(
                EC.presence_of_element_located((
                    By.XPATH,
                    "//input[string-length(substring-before(@id, '_181')) >= 0 and \
                        string-length(substring-after(@id, '_181')) = 0 and contains(@id, '_181')]"
                ))
            )
            fileNameField.send_keys(new_metadata["filename"])
        
        if new_metadata["author"] is not None:
            authorField = self.webdriver.find_element(
                By.XPATH,
                "//input[string-length(substring-before(@id, '_185')) >= 0 and \
                    string-length(substring-after(@id, '_185')) = 0 and contains(@id, '_185')]"
            )
            authorField.send_keys(new_metadata["author"])
        
        if new_metadata["license"] is not None:
            licenseField = Select(self.webdriver.find_element(By.CSS_SELECTOR, ".fp-license select"))
            licenseField.select_by_value(new_metadata["license"])
        
        if new_metadata["path"] is not None:
            pathField = Select(self.webdriver.find_element(By.CSS_SELECTOR, ".fp-path select"))
            pathField.select_by_value(new_metadata["path"])
        
        updateBtn = self.webdriver.find_element(By.CLASS_NAME, "fp-file-update")
        updateBtn.click()
        

    def file_is_accepted(self, filename):
        try:
            self.wait_driver(
                EC.presence_of_element_located((
                    By.XPATH,
                    f"//*[contains(@class,'fp-filename') and (text()='{filename}')]"
                ))
            )
            return True
        except:
            return False

    def upload_file(self, filename):
        self.webdriver.get(self.ENDPOINT)
        uploadBtn = self.wait_driver(
            EC.element_to_be_clickable((
                By.XPATH,
                "//*[contains(@class,'btn') and (@title='Add...')]"
            ))
        )
        uploadBtn.click()
        fileUpload = self.wait_driver(
            EC.presence_of_element_located((By.NAME, "repo_upload_file")), 10
        )
        fileUpload.send_keys(self.CWD + config["dataDir"] + "/" + filename)
        confirmUpload = self.webdriver.find_element(
            By.CLASS_NAME,
            "fp-upload-btn"
        )
        self.webdriver.execute_script("arguments[0].click();", confirmUpload)


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