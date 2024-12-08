import logging
import os
from project.setting import LOG_DIR, APP_NAME, LOGGING_ENABLED

os.makedirs(LOG_DIR, exist_ok=True)

def setup_logger():
    if LOGGING_ENABLED:
        logging.basicConfig(
            filename=os.path.join(LOG_DIR, f"{APP_NAME.lower()}_log.txt"),
            level=logging.INFO,
            format="%(asctime)s - %(levelname)s - %(message)s",
            datefmt="%Y-%m-%d %H:%M:%S",
        )
    return logging.getLogger(APP_NAME)

logger = setup_logger()
