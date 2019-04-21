
require "json"

package = JSON.parse(File.read(File.join(__dir__, "../package.json")))

Pod::Spec.new do |s|
  s.name         = "RNAccountPickerReactNative"
  s.version      = package["version"]
  s.summary      = "RNAccountPickerReactNative"
  s.description  = <<-DESC
                  RNAccountPickerReactNative
                   DESC
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.author             = { "author" => package["author"] }
  s.platform     = :ios, "9.0"
  s.source       = { :git => "https://github.com/dream11/account-picker-react-native.git", :tag => "#{s.version}" }
  s.source_files  = "*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  
end

  