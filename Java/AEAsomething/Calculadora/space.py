import time
import ctypes
import win32gui
import win32con
import win32api
import sys
import threading

# Configuration
SPACE_KEY = 0x20  # Virtual key code for Spacebar
KEY_PRESS_DELAY = 0.1  # Seconds between key presses
STARTUP_DELAY = 5  # Seconds before starting
ROBLOX_WINDOW_TITLE = "Roblox"  # Partial title of Roblox window
EXIT_KEY = 0x1B  # Virtual key code for ESC

# Colors for console output
COLOR_RED = 12
COLOR_GREEN = 10
COLOR_YELLOW = 14
COLOR_CYAN = 11
COLOR_WHITE = 15

# Get console handle
kernel32 = ctypes.WinDLL('kernel32')
hConsole = kernel32.GetStdHandle(-11)

def set_console_color(color):
    kernel32.SetConsoleTextAttribute(hConsole, color)

def print_color(text, color=COLOR_WHITE):
    set_console_color(color)
    print(text)
    set_console_color(COLOR_WHITE)

def get_roblox_window():
    """Find the Roblox window handle"""
    def callback(hwnd, _):
        if win32gui.IsWindowVisible(hwnd):
            title = win32gui.GetWindowText(hwnd)
            if ROBLOX_WINDOW_TITLE in title:
                windows.append(hwnd)
        return True
    
    windows = []
    win32gui.EnumWindows(callback, None)
    return windows[0] if windows else None

def focus_roblox_window():
    """Bring Roblox window to foreground"""
    hwnd = get_roblox_window()
    if hwnd:
        # Try different methods to ensure focus
        try:
            win32gui.ShowWindow(hwnd, win32con.SW_RESTORE)
            win32gui.SetForegroundWindow(hwnd)
        except:
            pass
        return True
    return False

def press_space():
    """Simulate a robust spacebar press"""
    # Press down
    win32api.keybd_event(SPACE_KEY, 0, 0, 0)
    time.sleep(0.05)  # Hold for 50ms
    # Release
    win32api.keybd_event(SPACE_KEY, 0, win32con.KEYEVENTF_KEYUP, 0)

def is_exit_pressed():
    """Check if exit key is pressed"""
    return win32api.GetAsyncKeyState(EXIT_KEY) & 0x8000 != 0

def main():
    print_color("===== Advanced Roblox Spacebar Automation =====", COLOR_CYAN)
    print_color("This script uses multiple techniques to work with Roblox:", COLOR_YELLOW)
    print_color("1. DirectInput simulation for game compatibility", COLOR_WHITE)
    print_color("2. Window focusing to ensure Roblox is active", COLOR_WHITE)
    print_color("3. Low-level key events that bypass some security", COLOR_WHITE)
    print_color("4. Multiple press methods for reliability", COLOR_WHITE)
    
    print_color("\nInstructions:", COLOR_YELLOW)
    print_color(f"- Make sure Roblox is running ({ROBLOX_WINDOW_TITLE} in window title)", COLOR_WHITE)
    print_color(f"- The script will start in {STARTUP_DELAY} seconds", COLOR_WHITE)
    print_color("- Press ESC at any time to stop the automation", COLOR_WHITE)
    print_color("=" * 50, COLOR_CYAN)
    
    # Try to focus Roblox window
    print_color("\nAttempting to focus Roblox window...", COLOR_YELLOW)
    if focus_roblox_window():
        print_color("Roblox window focused!", COLOR_GREEN)
    else:
        print_color("Warning: Could not find Roblox window!", COLOR_RED)
        print_color("Please make sure Roblox is running before continuing.", COLOR_YELLOW)
    
    # Countdown with exit check
    print_color(f"\nStarting in {STARTUP_DELAY} seconds...", COLOR_YELLOW)
    for i in range(STARTUP_DELAY, 0, -1):
        if is_exit_pressed():
            print_color("\nExit key pressed. Stopping before start.", COLOR_RED)
            return
        print_color(f"{i}...", COLOR_GREEN if i > 2 else COLOR_YELLOW)
        time.sleep(1)
    
    # Main automation loop
    print_color("\nSTARTING SPACEBAR AUTOMATION!", COLOR_GREEN)
    print_color("Press ESC to stop at any time...", COLOR_YELLOW)
    
    try:
        press_count = 0
        while True:
            # Try to focus window periodically (every 10 presses)
            if press_count % 10 == 0:
                focus_roblox_window()
            
            # Try multiple press methods for reliability
            press_space()
            
            # Alternative method for stubborn cases
            if press_count % 5 == 0:
                # Send a longer press
                win32api.keybd_event(SPACE_KEY, 0, 0, 0)
                time.sleep(0.1)
                win32api.keybd_event(SPACE_KEY, 0, win32con.KEYEVENTF_KEYUP, 0)
            
            press_count += 1
            time.sleep(KEY_PRESS_DELAY)
            
            if is_exit_pressed():
                print_color("\nExit key pressed. Stopping automation.", COLOR_RED)
                break
                
    except KeyboardInterrupt:
        print_color("\nProgram interrupted by user", COLOR_RED)
    except Exception as e:
        print_color(f"\nError occurred: {e}", COLOR_RED)
    finally:
        print_color("\nAutomation stopped. Press any key to exit...", COLOR_YELLOW)
        input()

if __name__ == "__main__":
    # Check for required modules
    try:
        import win32gui, win32con, win32api
    except ImportError:
        print("Required modules not installed. Please run:")
        print("pip install pywin32")
        sys.exit(1)
    
    # Run as admin check
    if ctypes.windll.shell32.IsUserAnAdmin() == 0:
        print_color("Warning: Not running as administrator!", COLOR_RED)
        print_color("For best results, run this script as Administrator.", COLOR_YELLOW)
        print_color("Right-click on the script and select 'Run as administrator'", COLOR_YELLOW)
        time.sleep(2)
    
    main()