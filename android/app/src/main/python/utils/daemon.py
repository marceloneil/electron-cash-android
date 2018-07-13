from lib import daemon, SimpleConfig #, WalletStorage

def get_daemon():
    config_options = {
        "cmd": "daemon",
        "verbose": True
    }
    config = SimpleConfig(config_options)
    # storage = WalletStorage(config.get_wallet_path())
    fd, server = daemon.get_fd_or_server(config)
    d = daemon.Daemon(config, fd, True)
    d.start()
    return d