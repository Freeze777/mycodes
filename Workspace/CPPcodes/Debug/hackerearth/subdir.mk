################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CC_SRCS += \
../hackerearth/easylife.cc \
../hackerearth/template.cc 

CC_DEPS += \
./hackerearth/easylife.d \
./hackerearth/template.d 

OBJS += \
./hackerearth/easylife.o \
./hackerearth/template.o 


# Each subdirectory must supply rules for building sources it contributes
hackerearth/%.o: ../hackerearth/%.cc
	@echo 'Building file: $<'
	@echo 'Invoking: Cross G++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


